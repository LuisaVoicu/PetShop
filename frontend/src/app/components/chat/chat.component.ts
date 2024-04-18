import { Component, ElementRef, OnInit, QueryList, ViewChildren } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChatMessage } from '../../models/ChatMessage';
import { ChatService } from '../../services/chat/chat.service';
import { User } from '../../models/User';
import { AxiosService } from '../../axios.service';
@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  messageInput: string = '';
  // user: User = {
  //   id:0,
  //   firstName: '',
  //   lastName: '',
  //   username: '',
  //   email_address: '',
  //   imageurl: '',
  //   loginTime: new Date()
  // };
  userId: number=0;

  messageList: any[] = [];

  messageListTest: string[] = [];
  
  constructor(private axiosService: AxiosService,private chatService: ChatService,
    private route: ActivatedRoute
    ){}

  ngOnInit(): void {
    this.userId =  this.route.snapshot.params["userId"]; 
    this.chatService.joinRoom("ABC");
    this.listenerMessage();
  }


  getOldMessages(){

    this.axiosService.request(
      "GET",
      "/user-messages",
      this.userId
    ).then(
      (response) => {

        this.messageListTest = response.data;
        console.log("in chat.component, messages from backend for user:" + this.messageListTest);

      })
      .catch((error) => {
        console.error(error);
      });
  }

  sendMessage() {

    this.getOldMessages();

     //firstMessage = this.messageListTest[0];

      console.log("--------> " + this.messageListTest[0])

    const chatMessage = {
      //message: this.messageInput,
      message: this.messageListTest[0],
      user: this.userId
    }as ChatMessage
    //console.log("in chat.component sendMessage: "  + chatMessage.message)

    this.chatService.sendMessage("ABC", chatMessage);
    this.messageInput = '';
   
  }

  listenerMessage() {


    console.log("hello from listenerMessage!")

    this.chatService.getMessageSubject().subscribe((messages: any) => {
      this.messageList = messages.map((item: ChatMessage)=> ({
        ...item,
        message_side: item.user === this.userId ? 'sender': 'receiver'
      }))
    });
  }


  // listenerMessage() {
  //   console.log("hello from listenerMessage!");
  
  //   this.chatService.getMessageSubject().subscribe((messages: any) => {
  //     this.messageList = messages.map((item: ChatMessage) => {
  //       const messageSide = item.user === this.userId ? 'sender' : 'receiver';
  //       return {
  //         ...item,
  //         message_side: messageSide
  //       };
  //     });
  //   });
  // }
}