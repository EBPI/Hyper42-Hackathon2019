<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>submitDataUsingPOST</name>
   <tag></tag>
   <elementGuidId>37b3b0ac-58fe-4ed5-ab38-aff218d506e6</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;Authorisation\&quot;: [\n    {\n      \&quot;ClaimName\&quot;: \&quot;${ClaimName}\&quot;,\n      \&quot;Role\&quot;: [\n        \&quot;${Role}\&quot;\n      ],\n      \&quot;Where\&quot;: [\n        \&quot;${Where}\&quot;\n      ],\n      \&quot;Who\&quot;: [\n        \&quot;${Who}\&quot;\n      ]\n    }\n  ],\n  \&quot;passportData\&quot;: \&quot;${passportData}\&quot;,\n  \&quot;photo\&quot;: \&quot;${photo}\&quot;,\n  \&quot;travelData\&quot;: \&quot;${travelData}\&quot;\n}\n&quot;,
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
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>b216a744-22c4-484c-b404-3e6eb2df3f55</id>
      <masked>false</masked>
      <name>ClaimName</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>cd8da456-ceb6-4106-9532-141fdc2c16ed</id>
      <masked>false</masked>
      <name>Role</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>f5b1cff2-f983-4cfa-8146-924fb086bfa7</id>
      <masked>false</masked>
      <name>Where</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>7c4e4369-fdb1-4733-a96e-794487975393</id>
      <masked>false</masked>
      <name>Who</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>443dcb09-251f-4fe8-8a34-de7a4d9e49d4</id>
      <masked>false</masked>
      <name>passportData</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>acbfba01-3401-4b6a-8ebe-7fa5d5f32d7f</id>
      <masked>false</masked>
      <name>photo</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>f54512f7-0a25-4a07-8283-8b668b7e8297</id>
      <masked>false</masked>
      <name>travelData</name>
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
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
