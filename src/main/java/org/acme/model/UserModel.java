package org.acme.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // name = nome da coluna que irá armazenar a chave estrangeira
    // na tabela Message (solução a partir da JPA 2)
    @JoinColumn(name = "user_id")
    private List<MessageModel> messages;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ChannelModel> channels;

    public UserModel() {
        this.messages = new ArrayList<>();
        this.channels = new ArrayList<>();
    }

    public void addMessage(MessageModel message) {
        this.messages.add(message);
    }

    public void addChannel(ChannelModel channel) {
        this.channels.add(channel);
    }
}
