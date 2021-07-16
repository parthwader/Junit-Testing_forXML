# Junit-Testing

## To-Run##
1. Run the serializer
2. Run SFOToXml
3. Run XmlToObject
4. Run JunitTest or tetsRunner

## Libraries tested till now ##

#### 1. [GSON](https://github.com/google/gson) ####

**Relevant links**
*  [doc for testing base cases](https://cisco-my.sharepoint.com/:w:/p/vipulgup/EU4dLd-k8BhPuwCf7l3S8yYBUcsuJFCnCsE5ZYbiHs2eBQ?e=MDnvr7)
* Links to branch for [Serializing](https://wwwin-github.cisco.com/ntd-cloud-assist/VersionAdapterPart2/tree/deserializing-using-gson) and [Deserializing](https://wwwin-github.cisco.com/ntd-cloud-assist/VersionAdapterPart1/tree/serializing-using-gson) using GSON.

**Conclusion**

GSON approach was discarded due to following reasons - 
* Inability to properly serialize & deserialize `Map<object,something>(pgPoliciesMap to be specific)`. This will be an issue with all the JSON conversion libraries, as with JSON key can only be string and not an object and hence `Map<object,something>` didnt get properly serialized.
* Inability to serialize - deserialize DualHashBidiMap.

#### 2. [Jackson](https://github.com/FasterXML/jackson) ####

**Relevant links**
* Link to branch for [Serializing](https://wwwin-github.cisco.com/ntd-cloud-assist/VersionAdapterPart1/tree/serializing-using-jackson) using jackson.

**Conclusion**
* Jackson approach was discarded due to same reason as for GSON. Therefore didnt proceeded further with deserializing.

#### 3. [Kryo](https://github.com/EsotericSoftware/kryo) ####

**Relevant links**
* [doc for testing base cases](https://cisco-my.sharepoint.com/:w:/p/vipulgup/EXd0vX08uaRFj1Awj7LA8xUBpx09tI92EkEsCz5xIDnMNw?e=8qAqOG)
* Link to branch for [Serializing](https://wwwin-github.cisco.com/ntd-cloud-assist/VersionAdapterPart1/tree/serializing-using-kryo) & [Deserializing](https://wwwin-github.cisco.com/ntd-cloud-assist/VersionAdapterPart1/tree/serializing-using-kryo) using kryo.

**Conclusion**

Kryo approach was discarded on following grounds - 
* Inability to work properly with enums, as it concerns itself with order of values in enums and not directly with value.
* Failing to properly deserialize, pgPoliciesMap and giving this weird [error](https://wwwin-github.cisco.com/ntd-cloud-assist/VersionAdapterPart2/blob/deserializing-using-kryo/error.txt)
* Cant write specific serializer/deserializer for pgPoliciesMap. Due to type-erasure in java, that serializer/deserializer was used by all map objects.
* Lack of proper support on google, stackoverflow.

#### 4. Version Ignorance Method testing ####

**Relevants links**
* [Doc Link](https://cisco-my.sharepoint.com/:w:/p/vipulgup/EV0m4utzH8ZBmNHNSA3S8tgBkgUAbnYH4U7TzQWhSDVsHQ?e=eXKXte)
* Link to branch for [Deserializing](https://wwwin-github.cisco.com/ntd-cloud-assist/VersionAdapterPart2/tree/version-ignorance) by version ignorance.

**Current Situation**
* Giving `StreamCorruptionError` if we add an attribute in latest version of our class.
* Giving `InvalidObjectException`, if it couldnt find corresponding value in enum.
* Also, giving error related to `BasePolicyContainer$override` while deserializing PoliciesImportExportData object.


#### 5. [XStream](https://github.com/x-stream/xstream) ####

**Relevant links**
* Link to branch for [Serializing](https://wwwin-github.cisco.com/ntd-cloud-assist/VersionAdapterPart1/tree/serializing-XML-Xstream-approach) & [Deserializing](https://wwwin-github.cisco.com/ntd-cloud-assist/VersionAdapterPart2/tree/deserializing-XML-Xstream-approach) using XStream.
* [Doc link](https://cisco-my.sharepoint.com/:w:/p/vipulgup/EcYdf_fBH-FDvRL6Qpy8PHQBtWE0l0A1NY_ASvXa_SO_0A?e=baCMF3)

**Current Situation**
* Wasnt able to handle `DualHashBidiMap` serialization and deserialization, as it is not standard jdk object.
* Had to write custom serializer and deserializer for `DualHashBidiMap`.
* Apart from above point, works perfectly with few warnings.
* Only library till now, which was able to handle `pgPoliciesMap`.

#### 6. JAVAX ####
**Relevant link**
* [Doc link](https://cisco-my.sharepoint.com/:w:/p/vipulgup/EcYdf_fBH-FDvRL6Qpy8PHQBtWE0l0A1NY_ASvXa_SO_0A?e=baCMF3)

**Conclusion**
It was discarded since it needed to modify the class by adding annotations:

* XMLRootElement for the class whose object has to be serialized
* XMLElement : for attributes which we want to have in the serialized stream

#### 7. JavaBeans ####

**Relevant links**
* Link to [Serializing](https://wwwin-github.cisco.com/ntd-cloud-assist/VersionAdapterPart1/tree/serializing-using-XML) branch
* [doc for testing base cases](https://cisco-my.sharepoint.com/:w:/p/vipulgup/EcYdf_fBH-FDvRL6Qpy8PHQBtWE0l0A1NY_ASvXa_SO_0A?e=baCMF3)

**Current Situtation**
* Error 1: FakeLogin, Cannot generate fresh id Solution: Add a ProxyVms service to bypass the id generation
* Error 2: giving a null pointer exception while trying to serialize the dualHashBidiMap
* Error 3: While writing the object into the encoder, it creates a new instance of the object which causes errors.

#### 8. Jackson-Xml ####

**Relevant Links**
* Link to [Serializing](https://wwwin-github.cisco.com/ntd-cloud-assist/VersionAdapterPart1/tree/serializing-using-jackson) & [Deserializing](https://wwwin-github.cisco.com/ntd-cloud-assist/VersionAdapterPart2/tree/XML-Jackson) branch
* [doc for testing base cases](https://cisco-my.sharepoint.com/:w:/p/vipulgup/EcYdf_fBH-FDvRL6Qpy8PHQBtWE0l0A1NY_ASvXa_SO_0A?e=baCMF3)

**Conclusion**
Discarded due the following reasons:
* Inability to properly serialize `Map<object,something>(pgPoliciesMap in our case)`.
In Jackson serialization of map,the key can not be an object itself. If the object is a key,then it does not get serialized and leads to error in desrializing it
WorkArounds:
* Can use a overide `toString()` function to convert the key object to a string
* Can use custom serializers and deserializers
However neither of these two provide a generic solution and require modifications of the classes
