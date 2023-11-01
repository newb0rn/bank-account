import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Operation} from "./operation.entity";
import {PrintingStatement} from "./printing-statement.entity";

@Injectable({
  providedIn: 'root'
})
export class OperationService {
  constructor(private http: HttpClient) {
  }

  public addOperation(operation: any): Observable<any> {
    return this.http.post<any>('http://localhost:8080/account/newOperation', operation);
  }

  public getPrintingStatement(accountNumber: string): Observable<PrintingStatement> {
    return this.http.get<PrintingStatement>('http://localhost:8080/account/' + accountNumber);
  }

}
