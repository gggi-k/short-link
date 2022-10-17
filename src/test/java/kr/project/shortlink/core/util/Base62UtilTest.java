package kr.project.shortlink.core.util;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UTIL")
public class Base62UtilTest {

    @CsvSource({
           "1                    ,001"
          ,"2                    ,002"
          ,"61                   ,00z"
          ,"62                   ,010"
          ,"63                   ,011"
          ,"65                   ,013"
          ,"124                  ,020"
          ,"125                  ,021"
          ,"238320               ,zzs"
          ,"238328               ,1000"
          ,"238329               ,1001"
          ,"922337203685477586  ,168JThSCug6"
          ,"9223372036854775807 ,AzL8n0Y58m7"
    })
    @ParameterizedTest(name = "base62 인코딩 - {index} 값 :{0} 결과 : {1}")
    void encode(long value, String result) {
        assertEquals(result, Base62Util.encode(value));
    }

    @CsvSource({
            "1                    ,001"
            ,"2                    ,002"
            ,"61                   ,00z"
            ,"62                   ,010"
            ,"63                   ,011"
            ,"65                   ,013"
            ,"124                  ,020"
            ,"125                  ,021"
            ,"238320               ,zzs"
            ,"238328               ,1000"
            ,"238329               ,1001"
            ,"922337203685477586  ,168JThSCug6"
            ,"9223372036854775807 ,AzL8n0Y58m7"
    })
    @ParameterizedTest(name = "base62 디코딩 - {index} 값 :{1} 결과 : {0}")
    void decode(long result, String value) {
        assertEquals(result, Base62Util.decode(value));
    }
}