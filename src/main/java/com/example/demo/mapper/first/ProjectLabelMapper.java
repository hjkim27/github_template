package com.example.demo.mapper.first;

import com.example.demo.bean.vo.project.ProjectLabelVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectLabelMapper {

    /**
     * <pre>
     *     라벨정보 추가
     * </pre>
     *
     * @param projectLabelVO
     * @return
     */
    public Long insertLabel(ProjectLabelVO projectLabelVO);

    /**
     * <pre>
     *     라벨정보 업데이트
     *     - set : name, description, color, active(true)
     *     - where : label_id
     * </pre>
     *
     * @param projectLabelVO
     * @return
     */
    public Integer updateLabel(ProjectLabelVO projectLabelVO);

    /**
     * <pre>
     *     모든 라벨의 active=false 업데이트
     * </pre>
     *
     * @return
     */
    public Integer updateLabelActiveFalse();

    /**
     * <pre>
     *     라벨이 존재하는지 확인
     *     where : label_id
     * </pre>
     *
     * @param labelId
     * @return
     */
    public Boolean isExistLabel(Long labelId);
}
