package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Message;
import com.bdehr.backend.Interface.MachineRepository;
import com.bdehr.backend.Interface.MessageRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class MessageService {
    @Autowired
    MessageRepository messageRepo;

    @PostMapping(path="message/add-message")
    private void addMessage(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String text = jo.getString("text");
        String sender = jo.getString("sender");

        Message message = new Message(text, sender, "unread");
        messageRepo.saveAndFlush(message);
    }

    @GetMapping(path="message/get-unread-cnt")
    private int getUnreadCnt(){
        List<Message> messageList = messageRepo.findByStatus("unread");
        return messageList.size();
    }

    @GetMapping(path="message/get-message-list")
    private List<Message> getMessageList(){
        List<Message> messageList = messageRepo.findAll();

        for(Message message: messageList){
            if(message.getStatus()=="unread") {
                message.setStatus("read");
                messageRepo.saveAndFlush(message);
            }
        }

        return messageList;
    }
}
