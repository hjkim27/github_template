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

    private GhRepositoryDTO repository = new GhRepositoryDTO();

    private List<GhLabelDTO> labels = new ArrayList<>();
    private List<GhCommentDTO> comments = new ArrayList<>();
    private List<GhEventDTO> events = new ArrayList<>();

    private String labelIds;

    public void addComment(GhCommentDTO comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }

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

    public void addEvent(GhEventDTO event) {
        if (events == null) {
            events = new ArrayList<>();
        }
        events.add(event);
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
