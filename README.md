# shopizer language pack generator

This software generates Shopizer labels for a new language. Shopizer comes with a few language packs already created such as english and frech; when a new language is required 2 options are possible (1) copy and create languages by hand or (2) use this software to auto-generate a new one.

#### requirements

  -- Most recent version of Shopizer on your machine
  -- An AWS (Amazon Web Service) account
  -- Java SDK (version 1.8 +)
  
#### prepare instructions  
  - Clone Shopizer from github
    - git clone -b master https://github.com/shopizer-ecommerce/shopizer.git
    - Look in shopizer/sm-shop/src/main/resources/bundles if language files are already there for required language (files have this format file_<2 letter language iso code>.properties (ex shopizer_fr.properties)
 - Add role to AWS user
    - Log on to AWS and add 'TranslateFullAccess' to your favorite user. See this document for more details https://docs.aws.amazon.com/IAM/latest/UserGuide/id_roles_create_for-user.html
 - Get your AWS access key and secret access key (more details here https://aws.amazon.com/premiumsupport/knowledge-center/create-access-key/)
 
#### generate language instructions 
  - Clone Language software from github
     - git clone -b master https://github.com/shopizer-ecommerce/languages.git
  - Build language application

```sh
cd LanguagePackBuilder
mvnw clean install
```

  - Execute generate language
    - Requires shopizer directory absolute path (1)
    - Requires target language 2 letter iso code (example es for Spanish) (2)
    - Requires AWS access key (3)
    - Requires AWS secret access key (4)
    - parameters 1 and 2 to be sent as execution parameters and parameters 3 and 4 to be sent as environment variables

```sh
cd target
java -jar LanguagePackBuilder-1.0.0-SNAPSHOT.jar com.shopizer.tools.language.LanguagePackBuilder path language -DAWS_ACCESS_KEY_ID=abd...xyz -DAWS_SECRET_ACCESS_KEY=xyz...123
```
#### Share your new language with us !

  - Sanitize and 2x check results before sharing
  - Open a pull request in Shopizer