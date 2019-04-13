#!/bin/bash

# Does everything necessary in order to start everything locally

# Prerequisites:
# 1. Always run this script from the folder it resides in.
# 2. Make sure the chaincode folder resides within the $GOPATH by, for instance, a symbolic link.

# ------------

# Function definitions:
# PHASE 0: CLEAN UP
function cleanupBackend {
  echo "---Closing running apps, if any...---"
  cd Backend
  docker-compose down
  cd ../Offchain
  docker-compose down
  cd ..
}

function cleanupLedger {
  cd blockchain-platform/bc-network
  echo "---Closing ledger...---"
  #Hyperledger
  ./build.sh down
  cd ../../
}

# PHASE 1: LEDGER
function runAndBuildLedger {
  # PHASE 1.1: DEPENDENCIES
  echo "---Running ledger start---"
  echo "---Starting gathering Fabric dependencies---"
  cd blockchain-platform
  ./get-docker-images.sh
  ./get-binaries.sh
  echo "---Gathering Fabric dependencies done---"

  # PHASE 1.2: STARTING FABRIC
  cd bc-network
  echo "---Generaring keyfiles for users---"
  ./build.sh generate
  echo "---Starting fabric...---"
  ./build.sh up
  echo "---Fabric has been started up.---"

  # PHASE 1.3: PREPARING, INSTALLING AND INSTANTIATING CHAINCODE
  echo "---Preparing chaincode with govendor dependency management...---"
  cd ../../blockchain/src/kim-chaincode
  ./govendor.sh
  echo "---Ready to install chaincode with govendor dependencies---"
  cd ../../../blockchain-platform/bc-network
  echo "---Installing current chaincode... Name: kimblockchain, version: 1.0---"
  ./installchaincode.sh kimblockchain 1.0
  echo "---Instantiating installed chaincode...---"
  ./instantiatechaincode.sh kimblockchain 1.0
  cd ../../
  echo "---Running ledger done...---"
}

# PHASE 2: PREPARING BACKEND APPLICATIONS
# PHASE 2.1: BUILDING BACKEND APPS
function buildBackend {
  echo "---Start building backend service...---"
  cd Backend
  mvn clean install
  echo "---Start building offchain service...---"
  cd ../Offchain
  mvn clean install
  cd ..
}

# PHASE 2.2: STARTING BACKEND APPS
function runBackend {
  cd Backend
  echo "---Start building backend services...---"
  echo "---Starting information service...---"
  nohup docker-compose up > target/backend_logs.out &
  sleep 5
  
  cd ../Offchain
  echo "---Starting contour service...---"
  nohup docker-compose up > target/offchain_logs.out &
  sleep 5
  
  cd ..
}

echo "---START OF RUNLOCAL SCRIPT---"

echo "******************************"
PS3='Select an option and press Enter: '
options=("Run complete (re-)install (first stop everything if running, then build and start backend apps and ledger, internet connection required when building the backend Docker images)" "Build and (re-)start ledger only (warning: will generate new certificates! Rebuilding backend apps will be required if you want to use them)" "Build and (re-)start backend apps only (warning: running ledger required! Will use the new certificates from the ledger, internet connection required when building the Docker images)" "(Re-)start backend apps only with the existing Docker images (warning: running ledger required! Ledger certificates will be expired when the running ledger build is more recent!" "Stop running backend apps and ledger")
select opt in "${options[@]}"
do
  case $opt in
        "Run complete (re-)install (first stop everything if running, then build and start backend apps and ledger, internet connection required when building the backend Docker images)")
          cleanupBackend
          cleanupLedger
          runAndBuildLedger
	  buildBackend
          runBackend
          ;;
        "Build and (re-)start ledger only (warning: will generate new certificates! Rebuilding backend apps will be required if you want to use them)")
          cleanupLedger          
          runAndBuildLedger
          ;;
        "Build and (re-)start backend apps only (warning: running ledger required! Will use the new certificates from the ledger, internet connection required when building the Docker images)")
          cleanupBackend
          buildBackend
          runBackend
          ;;
        "(Re-)start backend apps only with the existing Docker images (warning: running ledger required! Ledger certificates will be expired when the running ledger build is more recent!")
          cleanupBackend
          runBackend
          ;;
        "Stop running backend apps and ledger")
          cleanupBackend
          cleanupLedger
          ;;
        *) echo "invalid option";;
  esac
echo "Done"
exit
done
