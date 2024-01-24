mkdir target
mkdir target/resource
cp src/resource/it.bmp target/resource
javac src/java/edu/school21/printer/*/*.java -d ./target
java -cp target edu/school21/printer/application/ConsoleImageConverter