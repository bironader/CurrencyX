//
// Created by BiroKage on 7/30/2021.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring
JNICALL
Java_com_bironader_currencyx_Keys_apiKey(JNIEnv *env, jobject object) {
    std::string api_key = "REPLACE_YOUR_API_KEY";
    return env->NewStringUTF(api_key.c_str());
}

extern "C" JNIEXPORT jstring
JNICALL
Java_com_bironader_currencyx_Keys_baseUrl(JNIEnv *env, jobject object) {
    std::string baseUrl = "http://api.currencylayer.com/";
    return env->NewStringUTF(baseUrl.c_str());
}

