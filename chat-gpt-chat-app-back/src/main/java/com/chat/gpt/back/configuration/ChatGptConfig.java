package com.chat.gpt.back.configuration;

public class ChatGptConfig {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer "; // API 요청에 필요한 인증 방식 중 하나
    public static final String API_KEY = "open api 키 넣기"; 
    public static final String MODEL = "text-davinci-003";
    public static final Integer MAX_TOKEN = 1000; // 응답의 최대 길이를 제어
    public static final Double TEMPERATURE = 0.0; // 낮은 값일수록 보수적이고 일관된 응답을 생성하며, 높은 값일수록 다양하고 창의적인 응답
    public static final Double TOP_P = 1.0; // 방식을 사용하여 응답을 생성할 때, 생성 확률이 이 값 이상인 토큰들만을 고려하도록 지정하는 변수
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    public static final String URL = "https://api.openai.com/v1/completions";
}
