//package com.atharva.journalapp.service;
//
//import com.atharva.journalapp.entity.User;
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.ArgumentsProvider;
//
//import java.util.stream.Stream;
//
//public class UserArgumentsProvider implements ArgumentsProvider {
//
//    @Override
//    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
//        return Stream.of(
//                Arguments.of(User.builder().userName("AK").password("abc").build()),
//                Arguments.of(User.builder().userName("AB").password("abc").build())
//                );
//    }
//}
