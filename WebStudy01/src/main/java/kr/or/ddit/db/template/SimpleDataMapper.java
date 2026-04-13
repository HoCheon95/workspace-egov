package kr.or.ddit.db.template;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.commons.text.CaseUtils;

public class SimpleDataMapper {
    /**
     * ResultSet의 한 행(Row)을 자바 객체(DTO)로 변환해주는 메서드입니다.
     * @param rs DB에서 조회된 결과 집합
     * @param resultType 변환할 결과 객체의 클래스 타입 (예: MemberDto.class)
     * @return 데이터가 채워진 자바 객체
     * @throws SQLException
     */
    public static <T> T mapRow(ResultSet rs, Class<T> resultType) throws SQLException{
        try {
            // 기본 생성자를 호출하여 데이터를 담을 빈 객체를 생성한다.
            T result = resultType.getConstructor().newInstance();

            // 조회된 결과의 구조 정보(컬럼명, 개수 등)를 가져온다.
            ResultSetMetaData rsmd = rs.getMetaData();
            int clCnt = rsmd.getColumnCount(); // 전체 컬럼 개수 파악

            // 컬럼 개수만큼 반복하며 데이터를 객체에 주입한다.
            for (int idx = 1; idx<=clCnt; idx++) {
                // DB의 컬럼명(UNDER_SCORE)을 가져온다.
                String underScore = rsmd.getColumnName(idx);

                // DB 명명규칙을 자바의 카멜 표기법(camelCase)으로 변환한다.
                String camelCase = CaseUtils.toCamelCase(underScore, false, '_');
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(camelCase, resultType);
                    Method setter = pd.getWriteMethod();
                    Class<?> propertyType = pd.getPropertyType();

                    if (propertyType.equals(Integer.class)) {
                        int columnValue =rs.getInt(underScore);
                        setter.invoke(result, columnValue);
                    } else if (propertyType.equals(LocalDate.class)){
                        Date columnValue = rs.getDate(underScore);
                        if(columnValue!=null){
                            setter.invoke(result, columnValue.toLocalDate());
                        }
                        // setter.invoke(result, columnValue);
                    } else if (propertyType.equals(LocalDateTime.class)) {
                        Timestamp columnValue = rs.getTimestamp(underScore);
                        if (columnValue != null) {
                            setter.invoke(result, columnValue.toLocalDateTime());
                        }
                    } else {
                        Object columnValue = rs.getObject(underScore);
                        setter.invoke(result, columnValue);
                    }
                } catch (IntrospectionException e) {
                    System.err.println(e.getMessage());
                    continue;
                }
            }
            return result;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
