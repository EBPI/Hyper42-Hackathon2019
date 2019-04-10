package traveler

import (
	"crypto/sha256"
	"encoding/json"
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
	"strings"
)

type TravelerInformationPiece struct {
	Claim        string `json:"claim"`
	Response     string `json:"response"`
	RightToRead  string `json:"rightToRead"`
	WhereMayRead string `json:"whereMayRead"`
	WhoMayRead   string `json:"whoMayRead"`
}

type Bundle struct {
	Hash string `json:"hash"`
}

type Partial struct {
	Picture string `json:"picture"`
}

func RegisterClaim(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	id, e := APIstub.CreateCompositeKey("claim", []string{args[0]})
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Creating key failed")
	}
	fmt.Println(id)
	fmt.Println(id)
	fmt.Println(id)
	travelerInformationPiece := TravelerInformationPiece{
		Claim:        args[1],
		Response:     args[2],
		RightToRead:  args[3],
		WhereMayRead: args[4],
		WhoMayRead: args[5],
	}

	travelerInformationPieceAsBytes, e := json.Marshal(travelerInformationPiece)
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Creating json failed")
	}

	APIstub.PutState(id, travelerInformationPieceAsBytes)
fmt.Println(id)
	return shim.Success([]byte(id))
}

func ChallengeClaim(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {

	id, e := APIstub.CreateCompositeKey("claim", []string{args[0]})

	creator, creatorError := APIstub.GetCreator()
	if creatorError != nil {
		return shim.Error("Error extracting submitter information.")
	}
	fmt.Println(creator)

	travelerInformationPieceAsBytes, er := APIstub.GetState(id)
	var travelerInformationPiece TravelerInformationPiece
	err := json.Unmarshal(travelerInformationPieceAsBytes, &travelerInformationPiece)
	if e == nil &&
		er == nil &&
		err == nil &&
		travelerInformationPiece.Claim == args[1] &&
		strings.Contains(travelerInformationPiece.RightToRead, string(creator)) &&
		strings.Contains(travelerInformationPiece.WhereMayRead, args[3]) {
		return shim.Success([]byte(travelerInformationPiece.Response))

	}
	return shim.Error("Error, not found or insufficient rights")
}

func CheckHash(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	idHash := args[0]
	unhashed := args[1]

	for i := 2; i < len(args); i++ {
		if !isIdValid(APIstub, args[i]) {
			return shim.Error("Id is not valid")
		}
		unhashed = unhashed + args[i]
	}
	response, e := APIstub.GetState(idHash)
	if e != nil {
		shim.Error("retrieving hash failed")
	}

	hash := sha256.Sum256([]byte(unhashed))
fmt.Println("hash")
fmt.Printf("%x", hash)
	fmt.Println(" en of hash")
	if fmt.Sprintf("%x", hash) == fmt.Sprintf("%x", response) {
		return shim.Success(nil)
	} else {
		return shim.Error("fail")
	}

}

func RegisterHash (APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	 Bundle bundle{

	}
	bundel.Hash
	APIstub.PutState(args[0], args[1])
}


func CheckOfflineStorae(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	//hashlocation := args[0]

	for i := 1; i < len(args); i++ {

	}
return shim.Error("dsf")
}

func isIdValid(APIstub shim.ChaincodeStubInterface, id string) bool {
	response, e := APIstub.GetState(id)
	if response == nil || e != nil {
		return false
	}
	return true

}
