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
  title = 'Matières'

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
  ]

  newMat = {
    name: {
      text: '',
      value: ''
    },
    description: {
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
      }))
    }
    );
  }

  remove($event) {
    console.log($event)
  }
  save($event) {
    console.log($event)
  }
}
