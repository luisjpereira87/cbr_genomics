package com.example.demo.services;

import com.example.demo.constants.MessagesContants;
import com.example.demo.dto.NewsDTO;
import com.example.demo.exceptions.CustomException;
import com.example.demo.models.News;
import com.example.demo.models.Notifications;
import com.example.demo.models.Users;
import com.example.demo.repositories.INewsRepository;
import com.example.demo.repositories.INotificationsRepository;
import com.example.demo.repositories.IUserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private INewsRepository newRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private INotificationsRepository notificationsRepository;

    /**
     * Save new news
     *
     * @param newsDTO
     * @return
     * @throws Exception
     */
    public News save(NewsDTO newsDTO) throws CustomException {
        // Save news
        return this.newRepository.save(new News(newsDTO, this.getLoggedUser()));

    }

    /**
     * Get news by id and check if belongs to logger user
     *
     * @param id
     * @return
     */
    public News get(Long id) throws CustomException {

        return this.newRepository.findByIdAndUser(id, this.getLoggedUser())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND.ordinal(),
                        MessagesContants.NEWS_NOT_FOUND,
                        MessagesContants.NEWS_NOT_FOUND_TEXT));

    }

    /**
     * Get news list in draft
     *
     * @return
     * @throws CustomException
     */
    public List<News> getInDraft() throws CustomException {
        // Get logged user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return this.newRepository.findByIsPublish(false)
                    .orElseThrow(
                            () -> new CustomException(HttpStatus.NOT_FOUND.ordinal(),
                                    MessagesContants.NEWS_DRAFT_NOT_FOUND_TEXT,
                                    MessagesContants.NEWS_DRAFT_NOT_FOUND_TEXT));
        }

        throw new CustomException(101, MessagesContants.LOGGED_USER_NOT_ADMIN,
                MessagesContants.LOGGED_USER_NOT_ADMIN);

    }

    /**
     * Creates new relation between news and user, this relation simulate a vote If
     * the news has
     * multiples of 10 creates notifications
     *
     * @param id
     * @return
     * @throws CustomException
     */
    public News upvote(Long id) throws CustomException {

        News news = this.newRepository.findByIdAndIsPublish(id, true)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND.ordinal(),
                        MessagesContants.NEWS_NOT_FOUND,
                        MessagesContants.NEWS_NOT_FOUND_TEXT));

        // Create relation with user, simulate a vote
        Set<Users> usersSet = news.getVotes();
        usersSet.add(this.getLoggedUser());
        news.setVotes(usersSet);
        this.newRepository.save(news);

        // Check if size is multiple of 10
        if (usersSet.size() % 10 == 0) {

            List<Notifications> notificationsList = new ArrayList<>();

            for (Users user1 : usersSet) {
                Notifications notifications = new Notifications();
                notifications.setText("The news " + news.getText() + " reached " + usersSet.size());
                notifications.setUser(user1);
                notificationsList.add(notifications);
            }
            this.notificationsRepository.saveAll(notificationsList);
        }

        return news;
    }

    /**
     * Get the logged user and find in database
     *
     * @return
     * @throws CustomException
     */
    private Users getLoggedUser() throws CustomException {
        // Get logged user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get user of db by username
        return this.userRepository.findByUsername(authentication.getName())
                .orElseThrow(
                        () -> new CustomException(HttpStatus.NOT_FOUND.ordinal(), MessagesContants.NOT_FOUND,
                                MessagesContants.USER_NOT_FOUND));
    }
}
