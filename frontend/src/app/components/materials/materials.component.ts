import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { MaterialsService } from 'src/app/services/materials.service';

@Component({
  selector: 'app-materials',
  templateUrl: './materials.component.html',
  styleUrls: ['./materials.component.css']
})
export class MaterialsComponent implements OnInit {

  materials: any[];


  constructor(private materialService: MaterialsService) {
  }
  crudSubject: Subject<void> = new Subject<void>();
  title = 'matières'

  heads = [
    {
      label: 'Nom',
      field: 'name',
      type: 'text'
    },
    {
      label: 'Description',
      field: 'description',
      type: 'text'
    },
    {
      field: 'id',
      label: 'id',
      hidden: true,
      type: 'text',
      required: true
    },
  ]

  newMat = {
    name: {
      text: '',
      value: ''
    },
    description: {
      text: '',
      value: ''
    },
    id: {
      text: '',
      value: ''
    }
  }

  ngOnInit() {
    this.initMaterials();
  }

  initMaterials() {
    this.materialService.findAll().subscribe(r => {
      this.materials = r.map(val => ({
        name: {
          text: val.name,
          value: val.name
        },
        description: {
          text: val.description,
          value: val.description
        },
        id: {
          text: val.id,
          value: val.id
        },
      }))
    }
    );
  }

  remove($event) {
    this.materialService.remove($event.id.value).subscribe(r=>{
      this.crudSubject.next()
      this.initMaterials()
    },()=>this.crudSubject.next())
  }
  save($event) {
    let dto = {
      id: $event.id.value,
      description: $event.description.value,
      name: $event.name.value
    }
    this.materialService.save(dto).subscribe(r => {
      this.initMaterials();
      this.saveOK();
    })
  }

  saveOK() {
    this.crudSubject.next();
  }
}
