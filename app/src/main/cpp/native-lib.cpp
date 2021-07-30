//
// Created by BiroKage on 7/30/2021.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_package_name_Keys_apiKey(JNIEnv *env, jobject object) {
    std::string api_key = "YOUR_API_KEY";
    return env->NewStringUTF(api_key.c_str());
}
