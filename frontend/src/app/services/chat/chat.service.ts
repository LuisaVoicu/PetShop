import { Injectable } from '@angular/core';
import { Stomp } from '@stomp/stompjs';
import  SockJS from 'sockjs-client';
import { ChatMessage } from '../../models/ChatMessage';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private stompClient: any
  private messageSubject: BehaviorSubject<ChatMessage[]> = new BehaviorSubject<ChatMessage[]>([]);

  constructor() { 
    this.initConnenctionSocket();
  }

  initConnenctionSocket() {
    const url = 'http://localhost:8080/chat-socket'; // Use http instead of ws
    const socket = new SockJS(url);
    this.stompClient = Stomp.over(socket);
  }

  joinRoom(roomId: string) {
    this.stompClient.connect({}, ()=>{
      this.stompClient.subscribe(`/topic/${roomId}`, (messages: any) => {
        const messageContent = JSON.parse(messages.body);
        const currentMessage = this.messageSubject.getValue();
        currentMessage.push(messageContent);

        this.messageSubject.next(currentMessage);

      })
    })
  }


  sendMessage(roomId: string, chatMessage: ChatMessage) {
    console.log("chat.service :::::::: " + chatMessage.message)
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.send(`/app/chat/${roomId}`, {}, JSON.stringify(chatMessage));
    } else {
      console.error('STOMP client is not connected.');
    }
  }

  getMessageSubject(){
    return this.messageSubject.asObservable();
  }
}
