echo $(pwd)
java \
    -jar \
    -Djava.library.path="$(pwd)/../libs" \
    -Dorg.lwjgl.librarypath="$(pwd)/../libs/natives" \
    $(pwd)/../out/artifacts/demo/demo.jar