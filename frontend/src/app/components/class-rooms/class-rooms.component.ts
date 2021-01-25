import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { ClassRoomsService } from 'src/app/services/class-rooms.service';

@Component({
  selector: 'app-class-rooms',
  templateUrl: './class-rooms.component.html',
  styleUrls: ['./class-rooms.component.css']
})
export class ClassRoomsComponent implements OnInit {

  classRooms : any;
  title = 'salles de classe'
  crudSubject: Subject<void> = new Subject<void>();
  heads = [
    {
      field: 'id',
      label: 'Identifiant',
      type: 'text',
      disabled: true,
    },
    {
      field: 'name',
      label: 'Designation',
      type: 'text',
      required: true
    },
    {
      field: 'capacity',
      label: 'Capacité',
      type: 'number',
      required: true
    },

  ]
  newItem = {
    id: {
      text: '',
      value: 0
    },
    name: {
      text: '',
      value: ''
    },
    capacity: {
      text: 0,
      value: 0
    },
  }

  constructor(private classRoomsService : ClassRoomsService) { }

  ngOnInit(): void {
    this.fetchClassRooms();
  }


  private fetchClassRooms(){
      this.classRoomsService.findAll().subscribe(r=> {
        this.classRooms = r.map(val => ({
          id: {
            text: val.id,
            value: val.id
          },
  
          name: {
            text: val.name,
            value: val.name
          },
          capacity : {
            text : val.capacity, value : val.capacity
          }
        }))
      });
  }

  remove($event){
    this.classRoomsService.deleteById($event.id.value).subscribe(()=>this.fetchClassRooms(),()=>this.crudSubject.next())
  }
  save($event){
    console.log($event)
    let dto = {
      id : $event.id.value, name : $event.name.value , capacity : $event.capacity.value
    }
    this.classRoomsService.save(dto).subscribe(()=>{
      this.saveOK();
      this.fetchClassRooms();
    })
  } 
  onItemClick($event){
    console.log($event)
  } 

  private saveOK() {
    this.crudSubject.next();
  }

}
