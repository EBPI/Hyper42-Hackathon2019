package traveler

import (
	"crypto/sha256"
	"encoding/json"
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
	"strings"
)

type Statement struct {
	Claim                  string `json:"claim"`
	Response               string `json:"response"`
	SpecificReadPermission string `json:"rightToRead"`  // identities
	WhereMayRead           string `json:"whereMayRead"` // Schiphol or rotterdam Airport
	WhichRolesMayRead      string `json:"whoMayRead"`   // shops, lounge, alcoholic beverages shops
}

type Bundle struct {
	Hash string `json:"hash"`
}

type Partial struct {
	Picture string `json:"picture"`
}

func RegisterClaim(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	amountOfArguments := 6
	if len(args) != amountOfArguments {
		return shim.Error(fmt.Sprintf("Incorrect number of arguments. Expecting %d.", amountOfArguments))
	}

	id, e := APIstub.CreateCompositeKey("claim", []string{args[0]})
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Creating key failed")
	}
	travelerInformationPiece := Statement{
		Claim:                  args[1],
		Response:               args[2],
		SpecificReadPermission: args[3],
		WhereMayRead:           args[4],
		WhichRolesMayRead:      args[5],
	}

	travelerInformationPieceAsBytes, e := json.Marshal(travelerInformationPiece)
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Creating json failed")
	}

	APIstub.PutState(id, travelerInformationPieceAsBytes)
	return shim.Success([]byte(id))
}

func ChallengeClaim(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	amountOfArguments := 4
	if len(args) != amountOfArguments {
		return shim.Error(fmt.Sprintf("Incorrect number of arguments. Expecting %d.", amountOfArguments))
	}
	id, e := APIstub.CreateCompositeKey("claim", []string{args[0]})

	creator, creatorError := APIstub.GetCreator()
	if creatorError != nil {
		return shim.Error("Error extracting submitter information.")
	}

	travelerInformationPieceAsBytes, er := APIstub.GetState(id)
	var statement Statement
	err := json.Unmarshal(travelerInformationPieceAsBytes, &statement)
	if e == nil && er == nil && err == nil &&
		statement.Claim == args[1] &&
		strings.Contains(statement.SpecificReadPermission, string(creator)) &&
		strings.Contains(statement.WhereMayRead, args[3]) {
		return shim.Success([]byte(statement.Response))

	}
	return shim.Error("Error, not found or insufficient rights")
}

func CheckHash(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	amountOfArguments := 5
	if len(args) < amountOfArguments {
		return shim.Error(fmt.Sprintf("Incorrect number of arguments. Expecting at least %d.", amountOfArguments))
	}
	idHash, e := APIstub.CreateCompositeKey("bundle", []string{args[0]})
	if e != nil {
		shim.Error("recreating hashId failed")
	}

	unHashed := args[1]

	objectType := "pic"

	for i := 2; i < len(args); i++ {
		id, e := APIstub.CreateCompositeKey(objectType, []string{args[i]})
		if !isIdValid(APIstub, id) || e != nil {
			return shim.Error(id + " Id is not valid")
		}
		unHashed = unHashed + id
		objectType = "claim"
	}
	responseGetBundle, e := APIstub.GetState(idHash)
	if e != nil {
		shim.Error("retrieving hash failed")
	}

	var bundle Bundle

	err := json.Unmarshal(responseGetBundle, &bundle)
	if err != nil {
		shim.Error("unmarshal failed")
	}

	hash := sha256.Sum256([]byte(unHashed))

	if fmt.Sprintf("%x", hash) == bundle.Hash {
		return shim.Success(nil)
	} else {
		return shim.Error("fail")
	}

}

func RegisterHash(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	amountOfArguments := 3
	if len(args) < amountOfArguments {
		return shim.Error(fmt.Sprintf("Incorrect number of arguments. Expecting at least %d.", amountOfArguments))
	}
	id, e := APIstub.CreateCompositeKey("bundle", []string{args[0]})
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Creating key failed")
	}

	unHashed := args[1]

	objectType := "pic"

	for i := 2; i < len(args); i++ {
		id, e := APIstub.CreateCompositeKey(objectType, []string{args[i]})
		if !isIdValid(APIstub, id) || e != nil {
			return shim.Error(id + " Id is not valid")
		}
		unHashed = unHashed + id
		objectType = "claim"
	}

	hash := sha256.Sum256([]byte(unHashed))

	bundle := Bundle{
		Hash: fmt.Sprintf("%x", hash),
	}
	bundleAsBytes, e := json.Marshal(bundle)

	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Creating json failed")
	}

	e = APIstub.PutState(id, bundleAsBytes)
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Putting new state failed")
	}

	return shim.Success([]byte(id))

}

func isIdValid(APIstub shim.ChaincodeStubInterface, id string) bool {
	response, e := APIstub.GetState(id)
	if response == nil || e != nil {
		return false
	}
	return true

}
