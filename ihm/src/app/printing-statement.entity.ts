import {Operation} from "./operation.entity";

export interface PrintingStatement {
  balance: number,
  operations: Operation[]
}
