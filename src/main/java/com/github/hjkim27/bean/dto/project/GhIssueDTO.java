package com.github.hjkim27.bean.dto.project;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 *     gh_issue
 * </pre>
 *
 * @author hjkim27
 * @since 24.08.01
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GhIssueDTO {

    private Integer sid;
    private Long ghId;
    private Integer issueNumber;
    private String title;
    private String body;
    private String state;
    private Boolean pullRequest;    // 병합된 issue 일 경우 true
    private Boolean locked;
    private String htmlUrl;
    private String url;
    private Date createdAt;
    private Date updatedAt;
    private Date closedAt;

    private Long ghRepositoryId;
    private Integer repositorySid;

    private String labelIds;


    private String repositoryFullName;

    // 특정 issue 의 comment 목록
    private List<GhCommentDTO> commentList = new ArrayList<>();

    public void addComment(GhCommentDTO comment) {
        if (commentList == null) {
            commentList = new ArrayList<>();
        }
        commentList.add(comment);
    }

    // issue 목록 조회수정
    // null 체크 추가
    public List<Long> getLabelIdList() {
        List<Long> list = new ArrayList<>();
        if (labelIds != null && !labelIds.isEmpty()) {
            String[] arr = labelIds.split(",");
            for (String s : arr) {
                list.add(Long.parseLong(s));
            }
        }
        return list;
    }

    // 특정 issue 의 event 목록
    private List<GhEventDTO> eventList = new ArrayList<>();

    public void addEvent(GhEventDTO event) {
        if (eventList == null) {
            eventList = new ArrayList<>();
        }
        eventList.add(event);
    }

    // issue body 개행 확인로직 추가
    public String getBody() {
        if (this.body != null) {
            this.body = this.body.replaceAll("\\n", "<br>");
            return this.body;
        }
        return "";
    }

    // pullRequest, open, closed 구분 확인을 위함
    public String getType() {
        if (this.pullRequest) {
            return "Merged";
        } else {
            if (this.state != null) {
                String upper = this.state.substring(0, 1);
                String lower = this.state.substring(1).toLowerCase();
                return upper + lower;
            } else {
                return "";
            }
        }
    }
}
