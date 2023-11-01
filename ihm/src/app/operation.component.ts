import {Component, OnInit} from '@angular/core';
import {OperationService} from "./operation.service";
import {Operation} from "./operation.entity";

@Component({
  selector: 'app-root',
  templateUrl: './operation.component.html',
  styleUrls: ['./operation.component.css']
})
export class OperationComponent implements OnInit {
  operations: Operation[] = [];
  amountTyped: number = 0;
  accountNumber: string = "A123456789";
  balance: number = 0;
  displayedColumns: string[] = ['date', 'label', 'amount'];
  verificationDeposit: string;
  verificationPrintStatement: string;
  constructor(private operationService: OperationService) {
  }

  ngOnInit(): void {
    this.getAllOperations();
  }

  /**
   * Get all the operations of the account to display it
   */
  getAllOperations() {
    this.operationService.getPrintingStatement(this.accountNumber).subscribe(
      (data) => {
        this.operations = data.operations;
        this.balance = data.balance;
      },
      (error) => {
        this.verificationPrintStatement = error.error;
      }
    );
  }

  /**
   * Make a deposit on the account
   */
  deposit() {
    let newOperation: Operation = {} as Operation;
    newOperation.accountNumber = this.accountNumber;
    newOperation.amount = this.amountTyped;
    newOperation.label = "Deposit";
    this.operationService.addOperation(newOperation).subscribe(
      (response) => {
        this.getAllOperations();
        this.verificationDeposit = "Successful deposit of " + response + " €";
      },
      (error) => {
        this.verificationDeposit = error.error;
      }
    );
  }

  /**
   * Print account statement
   */
  printStatement() {
    window.print();
  }
}
