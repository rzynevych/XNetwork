package com.rz.xnetwork.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rz.xnetwork.dto.ChatListElem;
import com.rz.xnetwork.models.AppUser;
import com.rz.xnetwork.repos.ChatRepository;
import com.rz.xnetwork.security.UserHandler;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {
    
    private final ChatRepository chatRepository;

    public List<ChatListElem> getChatList(Long userId, int page, int size) {
       
        List<ChatListElem> users = chatRepository.getChatList(userId,
                PageRequest.of(page, size, Sort.by("lastUsed").descending()));
        return users;
    }
}
