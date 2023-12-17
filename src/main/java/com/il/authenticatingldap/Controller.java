package com.il.authenticatingldap;

import com.il.authenticatingldap.daoandrepo.Flashcard;
import com.il.authenticatingldap.daoandrepo.FlashcardRepository;
import com.il.authenticatingldap.daoandrepo.Video;
import com.il.authenticatingldap.daoandrepo.VideoRepository;
import com.il.authenticatingldap.model.IdNameTitle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class Controller {
    private final FlashcardRepository _fcRepo;
    private final VideoRepository _videoRepo;

    public Controller(FlashcardRepository _fcRepo, VideoRepository _videoRepo) {
        this._fcRepo = _fcRepo;
        this._videoRepo = _videoRepo;
    }

    @GetMapping("/")
    public String index() {
        return "Login Success!";
    }

    @GetMapping("/access-details")
    public String accessDetails(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return "Has access to " + userDetails.getAuthorities().stream().map(Object::toString).collect(Collectors.joining(","));
        } else {
            return "User not authenticated or doesn't have attributes.";
        }
    }

    @GetMapping("/fc")
    public String getFlashcards(Authentication authentication) {
        if (hasRole(authentication, "ROLE_FLASHCARDS")) {
            List<Flashcard> flashcards = _fcRepo.findAllById(getIds(20));
            Collections.sort(flashcards, Comparator.comparingLong(Flashcard::getId));
            String result = generateHtmlTable(flashcards.stream().map(X -> new IdNameTitle(X.getId(), "FC", X.getTitle())).collect(Collectors.toList()));
            return result;
        } else {
            return "no access";
        }
    }

    @GetMapping("/video")
    public String getVideos(Authentication authentication) {
        if (hasRole(authentication, "ROLE_VIDEOS")) {
            List<Video> videos = _videoRepo.findAllById(getIds(20));
            Collections.sort(videos, Comparator.comparingLong(Video::getId));
            String result = generateHtmlTable(videos.stream().map(X -> new IdNameTitle(X.getId(), "Video", X.getTitle())).collect(Collectors.toList()));
            return result;
        } else {
            return "no access";
        }
    }

    private List<Long> getIds(int limit) {
        List<Long> ids = new ArrayList<>();
        for (int i = 1; i < limit; i++) {
            ids.add((long) i);
        }
        return ids;
    }

    public static String generateHtmlTable(List<IdNameTitle> objectList) {
        StringBuilder tableHtml = new StringBuilder();
        tableHtml.append("<table>");

        // Create table header
        tableHtml.append("<tr>");
        tableHtml.append("<th>ID</th>");
        tableHtml.append("<th>Name</th>");
        tableHtml.append("<th>Title</th>");
        tableHtml.append("</tr>");

        // Create table rows
        for (IdNameTitle obj : objectList) {
            tableHtml.append("<tr>");
            tableHtml.append("<td>").append(obj.id).append("</td>");
            tableHtml.append("<td>").append(obj.name).append("</td>");
            tableHtml.append("<td>").append(obj.title).append("</td>");
            tableHtml.append("</tr>");
        }

        tableHtml.append("</table>");

        return tableHtml.toString();
    }

    private boolean hasRole(Authentication authentication, String role) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getAuthorities().stream().map(Object::toString).anyMatch(X -> Objects.equals(X, role));
        } else {
            return false;
        }
    }
}
