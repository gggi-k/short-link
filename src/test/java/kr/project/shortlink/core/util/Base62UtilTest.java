package kr.project.shortlink.core.util;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("UTIL")
public class Base62UtilTest {

    @ValueSource(longs = {1, 2, 61, 62, 63, 65, 124, 125, 238320, 238328, 238329, 922337203685477586L, Long.MAX_VALUE})
    @ParameterizedTest
    void encode(long value) {
        System.out.println(Base62Util.encode(value));
    }

    @ValueSource(strings = {
            "001"
            ,"002"
            ,"00z"
            ,"010"
            ,"011"
            ,"013"
            ,"020"
            ,"021"
            ,"zzs"
            ,"1000"
            ,"1001"
            ,"168JThSCug6"
            ,"AzL8n0Y58m7"})
    @ParameterizedTest
    void decode(String value) {
        System.out.println(Base62Util.decode(value));
    }
}