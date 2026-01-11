public class App {
    public static void main(String[] args) throws Exception {
        Database db = new Database();
        Database.add_data("25390808#en#a:72,b:6,c:19,d:20,e:62,f:8,g:7,h:16,i:42,j:2,k:9,l:19,m:17,n:53,o:33,p:16,r:43,s:49,t:46,u:10,v:2,w:6,x:1,y:5,z:1,");
        Database.add_data("75327291#en#a:178,b:22,c:59,d:80,e:252,f:56,g:66,h:59,i:149,j:5,k:14,l:74,m:45,n:143,o:141,p:50,q:1,r:143,s:111,t:139,u:69,v:24,w:18,x:3,y:38,z:13,");
        Database.add_data("15930075#en#ρ:1,ς:1,Α:1,a:47,b:3,c:7,d:24,e:61,f:7,g:13,h:15,i:32,k:5,l:17,ά:1,m:16,n:39,o:27,p:4,q:1,r:32,s:29,t:22,δ:2,u:9,ε:1,v:4,w:4,η:1,x:1,y:3,ν:1,");
        System.out.println(Database.get_data_language("en"));
    }
}
