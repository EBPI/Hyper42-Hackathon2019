<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>submitDataUsingPOST-Manual</name>
   <tag></tag>
   <elementGuidId>86f57832-973d-4db9-9f1f-8c662d04a903</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;dataHashLocation\&quot;:\&quot;73297974329794327\&quot;,\n  \&quot;dataHashSalt\&quot;:\&quot;98ed71bd58611629564986544d\&quot;,\n  \&quot;dataHash\&quot;:\&quot;38fad8c275f018bf504aa621fc9ce743\&quot;,\n  \&quot;claimAddresses\&quot;:[\&quot;358a138a-9c1c-4d19-89f9-6f8abb8f71ea\&quot;],\n  \&quot;photo\&quot;:\&quot;11111hskfglkasnvtrjgrsgshfdhdfhgcha1111\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>http://172.16.162.30:7090//data/</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>79410bcc-3c49-43f1-8b78-1571f8ab00e8</id>
      <masked>false</masked>
      <name>claimAddresses</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>0cfa918a-c260-4bae-a4f9-8c626cf15b81</id>
      <masked>false</masked>
      <name>dataHash</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>0bb716c9-1b3f-40ae-9761-5093ef88cce1</id>
      <masked>false</masked>
      <name>dataHashLocation</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>d1a12824-4731-4965-8846-59530908363c</id>
      <masked>false</masked>
      <name>dataHashSalt</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>44a10b40-16b2-4332-95a3-6b476801eed0</id>
      <masked>false</masked>
      <name>photo</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()



WS.verifyResponseStatusCode(response, 201)

assertThat(response.getStatusCode()).isEqualTo(201)</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
