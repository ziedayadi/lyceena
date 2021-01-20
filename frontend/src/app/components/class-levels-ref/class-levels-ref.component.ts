import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { ClassLevelsRefService } from 'src/app/services/class-levels-ref.service';

@Component({
  selector: 'app-class-levels-ref',
  templateUrl: './class-levels-ref.component.html',
  styleUrls: ['./class-levels-ref.component.css']
})
export class ClassLevelsRefComponent implements OnInit {

  classLevelRefs: any;
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

  ]
  title = 'Niveaux'
  newItem = {
    id: {
      text: '',
      value: 0
    },
    name: {
      text: '',
      value: ''
    },
  }
  crudSubject: Subject<void> = new Subject<void>();

  constructor(private classLevelRefService: ClassLevelsRefService,
    private router: Router) { }

  ngOnInit(): void {
    this.fetchClassLevelRefs();
  }

  remove($event) {
    let id = $event.id.value
    this.classLevelRefService.remove(id).subscribe(r => {
      this.fetchClassLevelRefs();
    })
  }

  save($event) {
    let dto = {
      id: $event.id.value, name: $event.name.value
    }
    this.classLevelRefService.save(dto).subscribe(r => {
      this.saveOK();
      this.fetchClassLevelRefs();
    })
  }

  fetchClassLevelRefs() {
    this.classLevelRefService.findAll().subscribe(r => {
      this.classLevelRefs = r.map(val => ({
        id: {
          text: val.id,
          value: val.id
        },

        name: {
          text: val.name,
          value: val.name
        },
      }))
    });
  }

  saveOK() {
    this.crudSubject.next();
  }
  onItemClick($event){
    this.router.navigateByUrl('/class-levels-ref/'+$event.id.value);
  }
}
