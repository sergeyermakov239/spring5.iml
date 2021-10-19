package sergey.ermakov.project1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class ApiController {
    private ArrayList<User> users=new ArrayList<>();
    private ArrayList<String> messages=new ArrayList<>();


//curl -X POST http://localhost:8080/users -H 'Content-Type: application/json' -d '{"name1":"Sergey","age1":16}'
    @PostMapping("users")
    public void addUser(@RequestBody User user1){
        users.add(user1);
    };
//curl -X DELETE http://localhost:8080/users/1
    @DeleteMapping("users/{index}")
    public void deleteUser(@PathVariable("index") Integer index){
        users.remove((int) index);
    };

    @GetMapping("users/{index}")
    public String getUser(@PathVariable("index") Integer index){
        return users.get(index).print();
    };

    @GetMapping("users")
    public String getUsers(){
        String s="";
        for (User u:users){
            s+=u.print();
        };
        return s;
    };
    //curl -X PUT http://localhost:8080/users/2 -H 'Content-Type: application/json' -d '21'
    @PutMapping("users/{index}")
    public void updateUser(@PathVariable("index") Integer index, @RequestBody Integer age1){
        users.get(index).setAge(age1);
    };
//curl -X POST http://localhost:8080/messages -H 'Content-Type: application/json' -d 'text3'
    @PostMapping("messages")
    public void addMessage(@RequestBody String text){
        messages.add(text);
    };
    @GetMapping("messages/{index}")
    public String getMessage(@PathVariable("index") Integer index){
        return messages.get((int ) index);
    }
    @GetMapping("messages")
    public ArrayList<String> printMessages(){
        return messages;
    };
    //curl -X DELETE http://localhost:8080/messages/3
    @DeleteMapping("messages/{index}")
    public void deleteMessage(@PathVariable("index") Integer index){
        messages.remove((int ) index);
    };
    //curl -X PUT http://localhost:8080/messages/2 -H 'Content-Type: application/json' -d 'text10'
    @PutMapping("messages/{index}")
    public void updateMessage(@PathVariable("index") Integer index,@RequestBody String text){
        messages.remove((int) index);
        messages.add(index,text);
    }
    @GetMapping("messages/search/{text}")
    public int getIndex(@PathVariable("text") String text){
        int n=-1;
        for (int i=messages.size()-1;i>=0;i=i-1){
            if (messages.get(i).contains(text)){
                n=i;
            }
        }
        return n;
    };
    @GetMapping("messages/count")
    public int getSize(){
        return messages.size();
    };
    // curl -X POST http://localhost:8080/messages/2/create -H 'Content-Type: application/json' -d 'text10'
    @PostMapping("messages/{index}/create")
    public void addMessage1(@PathVariable("index") Integer index, @RequestBody String text){
        messages.add(index,text);
    };
    //curl -X DELETE http://localhost:8080/messages/search/text
    @DeleteMapping("messages/search/{text}")
    public void deleteMessages(@PathVariable("text") String text){
        for (int i=0;i< messages.size();i++){
            if (messages.get(i).contains(text)){
                messages.remove((int) i);
                i=i-1;
            }
        }
    }

}
