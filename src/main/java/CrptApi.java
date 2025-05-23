import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.Queue;
import java.util.concurrent.*;

public class CrptApi {
    TimeUnit timeUnit;
    int timeCount;
    int requestLimit;
    Queue<Request> q = new ConcurrentLinkedQueue<>();
    public CrptApi(int timeCount, TimeUnit timeUnit, int requestLimit){
        this.timeCount=timeCount;
        this.timeUnit = timeUnit;
        this.requestLimit = requestLimit;
    }
    public void createDocument(Document document, String signature){
        Gson gson = new Gson();
        Request request = new Request("MANUAL", gson.toJson(document), signature, "LP_INTRODUCE_GOODS");
        q.add(request);
    }
}

class Request{
    String document_format;
    String product_document;
    String product_group;
    String signature;
    String type;
    public Request(String document_format, String product_document, String signature, String type){
        this.document_format = document_format;
        this.product_document = product_document;
        this.signature = signature;
        this.type = type;
    }
}

class Document{
    Description description;
    String doc_id;
    String doc_status;
    String doc_type;
    boolean importRequest;
    String owner_inn;
    String participant_inn;
    String producer_inn;
    LocalDate production_date;
    String production_type;
    Product[] products;
    LocalDate reg_date;
    String reg_number;
}
class Description{
    String participantInn;
}
class Product{
    String certificate_document;
    LocalDate certificate_document_date;
    String certificate_document_number;
    String owner_inn;
    String producer_inn;
    LocalDate production_date;
    String tnved_code;
    String uit_code;
    String uitu_code;
}
