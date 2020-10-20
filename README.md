# tm-generator

# Ön Kurulumlar
1.) Java JDK

2.) [Gradle](https://gradle.org/install/)

3.) Intellij Idea

4.) RabbitMQ  [Windows]()  [MacOS[]() | [Linux]()

5.) ELK  [Windows]() | [MacOS](https://logz.io/blog/elk-mac/) | [Linux]()

Başlatma
```sh
brew services start elasticsearch
```


# Notlar
1. Aşağıdaki komut ile projeyi başlatmayı unutmayınız.

```sh
react-native init MyAwesomeProject --template typescript 
```

# Kurulum

1. Repoyu bilgisayara alalım:

```sh
git clone https://github.com/volkanulutas/datingapp-react-native.git
```

2. Aşağıdaki dependency'leri kuralım:

```sh
cd datingapp-react-native
npm install
cd ios
pod install 
```
3. Ek olarak "navigation" modülü için kurulum yapalım. İşletim sistemleri için: [Ref:](https://reactnavigation.org/docs/getting-started)

Ana dizine gelelim.

1.)

```sh
yarn add @react-navigation/native
yarn add @react-navigation/stack
yarn add @react-navigation/bottom-tabs
```

veya
```sh
npm install @react-navigation/native
npm install @react-navigation/stack
npm install @react-navigation/bottom-tabs
```

2.)

```sh
yarn add react-native-reanimated react-native-gesture-handler react-native-screens react-native-safe-area-context @react-native-community/masked-view
```
veya
```sh
npm install react-native-reanimated react-native-gesture-handler react-native-screens react-native-safe-area-context @react-native-community/masked-view
```
3.)

```sh
yarn add react-native-gesture-handler
```
veya
```sh
npm install react-native-gesture-handler
```
4.) 


```sh
cd ios
pod install
cd ..
react-native link
```
4. Kurulumu tamamladık, projeyi emulator veya telefonda çalıştıralım:

```sh
react-native run-ios
react-native run-android
```

