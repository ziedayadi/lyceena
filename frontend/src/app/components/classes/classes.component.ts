import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { ClassLevelsRefService } from 'src/app/services/class-levels-ref.service';
import { ClassesService } from 'src/app/services/classes.service';

@Component({
  selector: 'app-classes',
  templateUrl: './classes.component.html',
  styleUrls: ['./classes.component.css']
})
export class ClassesComponent implements OnInit {

  crudSubject: Subject<void> = new Subject<void>();
  title: 'Classes'

  classes: [];
  newItem = {
    id: {
      text: '', value: 0
    },
    name: {
      text: '', value: ''
    },
    level: {
      text: '', value: ''
    }
  }

  heads = [

  ]

  constructor(private classesService: ClassesService,
    private classLevelsRefService: ClassLevelsRefService,
    private router: Router) { }

  ngOnInit(): void {
    this.initHeads();
    this.fetchClasses();
  }
  private initHeads() {
    this.classLevelsRefService.findAll().subscribe(r => {
      let classLevelsOptions = r.map(val => ({
        value: val.id,
        text: val.name
      }));

      this.heads.push({
        field: 'level',
        label: 'Niveau',
        type: 'select',
        options: classLevelsOptions,
        required: true,
      },
        {
          field: 'id',
          label: 'Identifiant',
          type: 'text',
          disabled: true,
          hidden: true,
          options: null,
        },
        {
          field: 'name',
          label: 'Designation',
          type: 'text',
          required: true,
          options: null,
        })
    })
    this.heads.push()
  }

  fetchClasses() {
    this.classesService.findAll().subscribe(r => {
      this.classes = r.map(val => ({
        id: {
          value: val.id, text: val.id
        },
        name: {
          value: val.name, text: val.name
        },
        level: {
          text: val.level.name,
          value: val.level.id
        }
      }))
    })
  }

  save($event) {
    let dto = {
      id: $event.id.value, name: $event.name.value, levelId : $event.level.value
    }
    this.classesService.save(dto).subscribe(r => {
      this.saveOK();
      this.fetchClasses();
    })
  }


  remove($event) {
    let classId = $event.id.value;
    this.classesService.remove(classId).subscribe(r=>this.fetchClasses())  }

  onItemClick($event) {
    this.router.navigateByUrl('/classes/'+$event.id.value);
  }
  
  saveOK() {
    this.crudSubject.next();
  }

}
