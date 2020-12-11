import { Component, OnInit } from '@angular/core';
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
  title = 'Matières'

  heads = [
    {
      label: 'Nom',
      field: 'name'
    },
    {
      label: 'Description',
      field: 'description'
    },
  ]

  ngOnInit() {
    this.initMaterials();
  }

  initMaterials() {
    this.materialService.findAll().subscribe(r => {
      this.materials = r
    }
    );
  }
}
