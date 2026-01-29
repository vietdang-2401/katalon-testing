<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>lọc và lấy danh sách năng lực</description>
   <name>GetList</name>
   <tag></tag>
   <elementGuidId>108e76bc-56cf-4abb-80f6-be5283acd2fd</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <authorizationRequest>
      <authorizationInfo>
         <entry>
            <key>bearerToken</key>
            <value>eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI3ektyMi1ua1E5Q2ZqMG9VOURkcU5NZVFRX2lsYmVGVWpIVVlJZ2ZvaFBnIn0.eyJleHAiOjE3NzAxODkyNDQsImlhdCI6MTc3MDE4OTE4NCwiYXV0aF90aW1lIjoxNzcwMTg3ODcxLCJqdGkiOiI4MmMzYzI1OC00MTE2LTQwOWMtODEzZi05YzJlM2Q4OWExYzAiLCJpc3MiOiJodHRwczovL3Nzby1kZXYucGlyYWdvLndvcmsvcmVhbG1zL21hc3RlciIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI2ZmIyYTA5ZC0xOGZmLTQyOGUtOGRkMS01ODQyZjJjYTlmYTAiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJjZm1zLWxvY2FsIiwic2lkIjoiMDEyYThkMTgtM2Q1Yy00NjFlLWEyOWQtMmY1MWJiNGVmNjA3IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0Il0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLW1hc3RlciIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsInByZWZlcnJlZF91c2VybmFtZSI6ImRhbmcubmd1eWVuLnZpZXQiLCJlbWFpbCI6ImRhbmcubmd1eWVuLnZpZXRAcGlyYWdvLndvcmsifQ.ZmaFe_cQI9oCZYVbgag54KdJ9velDOFfwK68zaq4reNNcQ08cd4mVgPwHHHFknEdLrDsvMBiYf_DGmrgAOV8EuUYjWCoFnFpbO5ymA2sl8Ew_PBWzpiyx_BWyYcCHcZHXpLgX0kLEBF7FIr1i27iz-CIvlcUwtVo6x4FPm9KYJIddMOPDoMgr_zER7heIQPEiACmat8Hz-hNqR9C6HjuSTrW5_e5FCRwzg-4F45bUxoVSjM2L2S2OTCkmvF8l7YtPEL8SCVBt_z9n-nFjwh9-FwhOyAfJ4S-l81xKus73KHyFmi2SZ8FrgunaRwqwhFLL57AAXtjdu5K3jRZBnynWA</value>
         </entry>
      </authorizationInfo>
      <authorizationType>Bearer</authorizationType>
   </authorizationRequest>
   <autoUpdateContent>true</autoUpdateContent>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>true</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${access_token}</value>
      <webElementGuid>a507d5af-42f0-4cb7-ad4d-74722adfabae</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>11.0.0</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>http://localhost:8000/api/admin/competencies?page=1&amp;per_page=20&amp;type[]=all&amp;total=5&amp;last_page=1&amp;name=no-data</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>25b8ffb4-ea5b-47e2-abde-3566371f895e</id>
      <masked>false</masked>
      <name>variable</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
