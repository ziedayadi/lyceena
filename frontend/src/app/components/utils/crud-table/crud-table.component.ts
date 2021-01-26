import { AfterViewInit, ChangeDetectorRef, Component, HostListener, Input, OnInit, Output, ViewChild } from '@angular/core';
import { MdbTableDirective, MdbTablePaginationComponent } from 'angular-bootstrap-md';
import { EventEmitter } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CrudTableDialogComponent } from '../crud-table-dialog/crud-table-dialog.component';
import { Observable, Subscription } from 'rxjs';
import { ValidationDialogComponent } from '../remove-validation-dialog/validation-dialog.component';



@Component({
  selector: 'app-crud-table',
  templateUrl: './crud-table.component.html',
  styleUrls: ['./crud-table.component.css']
})
export class CrudTableComponent implements OnInit, AfterViewInit {

  @Input()
  public heads: Head[];

  @Input()
  public elements;

  @Input()
  public clickable : Boolean = false;

  @Input()
  public title: String;

  @Input()
  public newItem: String;

  @Input()
  itemsPerPage = 5;

  dialogRef : any;

  private eventsSubscription: Subscription;
  
  @Input() events: Observable<void>;


  @Output() remove = new EventEmitter();

  @Output() save = new EventEmitter();

  @Output() itemClick = new EventEmitter();

  searchText = '';
  previous: string;

  @HostListener('input') oninput() {
    this.searchItems();
  }

  @ViewChild(MdbTablePaginationComponent, { static: true }) mdbTablePagination: MdbTablePaginationComponent;
  @ViewChild(MdbTableDirective, { static: true }) mdbTable: MdbTableDirective

  constructor(private cdRef: ChangeDetectorRef, public dialog: MatDialog) { }

  ngAfterViewInit() {
    this.mdbTablePagination.setMaxVisibleItemsNumberTo(this.itemsPerPage);
    this.mdbTablePagination.calculateFirstItemIndex();
    this.mdbTablePagination.calculateLastItemIndex();
    this.cdRef.detectChanges();

  }


  ngOnInit(): void {
    this.mdbTable.setDataSource(this.elements);
    this.previous = this.mdbTable.getDataSource();
    this.eventsSubscription = this.events.subscribe(() => this.dialogRef.close() );

  }

  getField(i, field) {
    return this.elements[i][field]
  }

  edit(index: any) {
    let editedItem = this.elements[index];
    this.dialogRef = this.dialog.open(CrudTableDialogComponent, {
      width: '800px',
      data: { 
        action : 'Modifier',
        title: this.title,
        fields : this.heads,
        editedItem : editedItem
      }
    });
    this.dialogRef.componentInstance.onSaveAction.subscribe(r=> {
      this.save.emit(r)
    })
  }


  add() {
    this.dialogRef = this.dialog.open(CrudTableDialogComponent, {
      width: '800px',
      data: { 
        action : 'Ajouter',
        title: this.title,
        fields : this.heads,
        editedItem : this.newItem
      }
    });
    this.dialogRef.componentInstance.onSaveAction.subscribe(r=> {
      this.save.emit(r)
    })
  }


  searchItems() {
    let fields: any[] = this.heads.map(h => (h.field))

    const prev = this.mdbTable.getDataSource();
    if (!this.searchText) {
      this.mdbTable.setDataSource(this.previous);
      this.elements = this.mdbTable.getDataSource();
    }
    if (this.searchText) {
      this.elements = this.mdbTable.searchLocalDataBy(this.searchText);
      this.mdbTable.setDataSource(prev);
    }
  }

  sortBy(head){
    var sortedArray: any[] = this.elements.sort((obj1, obj2) => {
      if (obj1[head.field].text > obj2[head.field].text) {
          return 1;
      }
  
      if (obj1[head.field].text < obj2[head.field].text) {
          return -1;
      }
  
      return 0;
  });
  this.mdbTable.setDataSource(sortedArray);
  }


  onItemClick(el) {
    if (this.clickable)
      this.itemClick.emit(el);
  }

  onRemove(e){
    this.dialogRef = this.dialog.open(ValidationDialogComponent, {
      width: '500px',
      data: { 
        message :  'Êtes-vous sûr de vouloir supprimer cet element de ' + this.title + '?'
      }
    });

    this.dialogRef.componentInstance.onYesAction.subscribe(r=> {
      this.remove.emit(e);
    })
  }
}

export class Head {
  label: string
  field: string
  hidden: Boolean
}
