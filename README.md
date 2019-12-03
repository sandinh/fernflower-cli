
```shell script
cd ..
git clone https://github.com/sandinh/fernflower.git
cd fernflower
./gradlew publish

cd ../fernflower-cli
sbt publishLocal

cd ..
curl -Lo /usr/local/bin/coursier https://git.io/coursier-cli &&
    chmod +x /usr/local/bin/coursier &&
    coursier --help

cd sfs2x-util2/tmp
unzip sfs2x-lib-2.13.jar
cd sfs2x-lib-2.13
coursier bootstrap --standalone com.sandinh:fernflower-cli_2.13:0.1 -o fernflower-cli
find sfs -type f -exec ./fernflower-cli {} src/ \;
```
