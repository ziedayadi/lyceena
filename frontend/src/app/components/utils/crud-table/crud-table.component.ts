import { AfterViewInit, ChangeDetectorRef, Component, Input, OnInit, ViewChild } from '@angular/core';
import { MdbTableDirective, MdbTablePaginationComponent } from 'angular-bootstrap-md';

@Component({
  selector: 'app-crud-table',
  templateUrl: './crud-table.component.html',
  styleUrls: ['./crud-table.component.css']
})
export class CrudTableComponent implements OnInit , AfterViewInit {

  @Input()
  public heads: Head[];

  @Input()
  public elements;

  @Input()
  public title : String;

  @Input()
  itemsPerPage = 5;

  @ViewChild(MdbTablePaginationComponent, { static: true }) mdbTablePagination: MdbTablePaginationComponent;
  @ViewChild(MdbTableDirective, { static: true }) mdbTable: MdbTableDirective

  constructor(private cdRef: ChangeDetectorRef) { }

  ngAfterViewInit() {
    this.mdbTablePagination.setMaxVisibleItemsNumberTo(this.itemsPerPage);
    this.mdbTablePagination.calculateFirstItemIndex();
    this.mdbTablePagination.calculateLastItemIndex();
    this.cdRef.detectChanges();

  }


  ngOnInit(): void {
    this.mdbTable.setDataSource(this.elements);
  }

  getField(i, field) {
    return this.elements[i][field]
  }

  edit(item: any) {
    console.log(item);
  }


  remove(item: any) {
    console.log(item);
  }

  add() {
    console.log('ADD')
  }

}

export class Head {
  label: string
  field: string
}
