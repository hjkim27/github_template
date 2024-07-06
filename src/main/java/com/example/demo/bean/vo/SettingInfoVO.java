package com.example.demo.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * <pre>
 *     설정정보 저장
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 06
 */

@Getter
@Setter
@AllArgsConstructor
public class SettingInfoVO {
    private int sid;
    private String key;
    private String value;
    private String description;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SettingInfoVO vo = (SettingInfoVO) obj;

        return Objects.equals(sid, vo.sid)
                && Objects.equals(key, vo.key)
                && Objects.equals(value, vo.value)
                && Objects.equals(description, vo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, key, value, description);
    }
}
