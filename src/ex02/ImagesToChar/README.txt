mkdir target
mkdir target/resource
cp src/resource/it.bmp target/resource
javac -cp src/lib/JCDP-4.0.2.jar:src/lib/jcommander-1.82.jar src/java/edu/school21/printer/*/*.java -d ./target
jar cvmf src/manifest.txt target/images-to-chars-printer.jar target/edu/school21/printer/application/ConsoleImageConverter.class
java -jar target/images-to-chars-printer.jar