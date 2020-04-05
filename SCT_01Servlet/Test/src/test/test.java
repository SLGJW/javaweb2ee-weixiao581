package test;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class test {
    public static void main(String[] args) throws Exception {
        int port=8080;
        ServerSocket server=new ServerSocket(port);
        //�Զ��˿ڣ����������Socket
        System.out.println("��ʼ�������˿ںţ�"+port);
        while(true){
            Socket client=server.accept();
            //������ȡ�ͻ�������socket��accept������һ�������������ڿͻ��˺ͷ����֮�佨����ϵ֮ǰһֱ�ȴ�����
            System.out.println("Incoming!!!");

            //��ȡ�׽��ֵ���������
            InputStream inp=client.getInputStream();
            DataOutputStream outp = new DataOutputStream(client.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(inp));


            String requestLine = br.readLine();
            System.out.println(requestLine);

            String headerLine = null;
            while((headerLine = br.readLine()).length()!=0){
                System.out.println(headerLine);
            }


            StringTokenizer tokens = new StringTokenizer(requestLine);
            tokens.nextToken();
            String fileName = tokens.nextToken();


            fileName = "." + fileName;

            String statusLine = null;
            String contentTypeLine = null;
            String entityBody = null;
            try {

                FileInputStream fis =null;
                fis = new FileInputStream(fileName);

                statusLine = "HTTP/1.0 200 OK" + "\r\n";
                if(fileName.endsWith(".htm")||fileName.endsWith(".html")) {
                    contentTypeLine = "Content-Type:" + "text/html" + "\r\n";
                }else if(fileName.endsWith(".jpg")||fileName.endsWith(".jpeg")) {
                    contentTypeLine = "Content-Type:" + "image/jpeg" + "\r\n";
                }else {
                    contentTypeLine = "Content-Type:" + "application/ocatet-stream" + "\r\n";
                }


                outp.writeBytes(statusLine);

                outp.writeBytes(contentTypeLine);

                outp.writeBytes("\r\n");


                byte[] arr = new byte[1024];
                int len = 0;
                while((len = fis.read(arr))!= -1) {
                    outp.write(arr, 0, len);
                }

                fis.close();
                outp.close();
                br.close();
                client.close();

            }catch(FileNotFoundException e){

                statusLine = "HTTP/1.0 404 Not Found"+"\r\n";
                contentTypeLine = "Content-Type:text/html"+"\r\n";
                entityBody = "<html>" + "<head><title>Not Found</title></head>" + "<body>Not Found</body></html>";

                outp.writeBytes(statusLine);

                outp.writeBytes(contentTypeLine);

                outp.writeBytes("\r\n");

                outp.writeBytes(entityBody);

                outp.close();
                br.close();
                client.close();

            }
        }
    }
}