package com.likelion.stepstone.notification;

import com.likelion.stepstone.chat.event.ChatSendEvent;
import com.likelion.stepstone.chat.model.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

//    @GetMapping("/read/new")
//    public String readNewNotification(Principal principal, Model model){
//        MarkingNotifications markingNotifications = new MarkingNotifications();
//
//        List<NotificationDto> dtos = notificationService.readNewNotifications(principal.getName());
//
//        markingNotifications.setNotifications(dtos);
//        model.addAttribute("notifications",markingNotifications);
//
//        return "navbar :: #dropdown-menu";
//    }

    /**
     * 페이지 refresh를 실행하지 않기 위해
     * ajax post 함수로 데이터를 전송함
     *
     * 브라우저 콘솔에 404 or 500 에러가 발생하지만, 영향은 없다.
     * @param id
     * @return
     */
    @PostMapping("/mark")
    public String markAsRead(Long id ){
//        List<NotificationDto> dtos = markingNotifications.getNotifications();
        System.out.println(id);

        notificationService.mark(id);
        return "markNotification";
    }

    @PostMapping("/mark/all")
    public String markAll(Principal principal){
        notificationService.markAll(principal.getName());
        return "markNotification";
    }

    @PostMapping("/chat/new")
    public String newChatArrived(Principal principal, String chatRoomId){
        notificationService.publishNewChat(principal.getName(), chatRoomId);
        return "newChatNotification";
    }

//    @PostMapping("/chatroom/enter/")
//    public String registerOnlineChatUser(Principal principal, String roomId){
//        notificationService.registerOnlineChatUser(principal.getName(), roomId);
//
//        return "onlineChatRoom";
//    }
}
