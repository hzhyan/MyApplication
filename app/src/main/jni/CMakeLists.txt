set(SRC_FILES
    test_hello_world.cpp
)

add_library(my_jni_test SHARED ${SRC_FILES})
target_link_libraries(my_jni_test)