<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>submitDataUsingPOST-Manual</name>
   <tag></tag>
   <elementGuidId>657bebb5-7569-4618-9365-dd09c50997f9</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;Authorisation\&quot;: [\n    {\n      \&quot;ClaimName\&quot;: \&quot;OlderEightteen\&quot;,\n      \&quot;Role\&quot;: [\n        \&quot;string\&quot;\n      ],\n      \&quot;Where\&quot;: [\n        \&quot;string\&quot;\n      ],\n      \&quot;Who\&quot;: [\n        \&quot;string\&quot;\n      ]\n    }\n  ],\n  \&quot;passportData\&quot;: \&quot;eyJuYW1lIjogIk15TmFtZSIsICJEYXRlT2ZCaXJ0aCI6ICIxOTk4LTA0LTEyIiwgIk5hdGlvbmFsaXR5IjogIk5ldGhlcmxhbmRzIiwgIkV4cGlyYXRpb25EYXRlIjogIjIwMjQtMDQtMjMiLCAiUGhvdG8iOiAiYmFzZTY0ZW5jb2RlZFBob3RvIn0\&quot;,\n  \&quot;photo\&quot;: \&quot;string\&quot;,\n  \&quot;travelData\&quot;: \&quot;eyJGbGlnaHROdW1iZXIiOiAiS0wxMjMiLCAiRGF0ZSI6ICIyMDE5LTA2LTEyIiwgIkRlcGFydHVyZSI6ICJBTVMiLCAiRGVwYXJ0dXJlQ291bnRyeSI6ICJOZXRoZXJsYW5kcyIsICJEZXN0aW5hdGlvbiI6ICJCUlUiLCAiRGVzdGluYXRpb25Db3VudHJ5IjogIkJlbGdpdW0iLCAiRmxpZ2h0Qmx1ZSI6ICJTaWx2ZXIifQ\&quot;\n}\n&quot;,
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
   <restUrl>http://172.16.162.30:7080//register/</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
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
