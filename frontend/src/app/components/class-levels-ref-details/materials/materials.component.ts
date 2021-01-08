import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ClassLevelsRefService } from 'src/app/services/class-levels-ref.service';
import { MaterialsService } from 'src/app/services/materials.service';

@Component({
  selector: 'class-level-ref-app-materials',
  templateUrl: './materials.component.html',
  styleUrls: ['./materials.component.css']
})
export class ClassDetailsMaterialsComponent implements OnInit {

  @Input() classLevelRefId;
  materials : any;
  materialsList : any;
  newMaterialFormGroup : FormGroup;
  newLevelMaterial : any; 
  nbhs = [1,2,3,4,5,6,7,8,9,10]

  constructor(private classLevelRefService : ClassLevelsRefService,
    private materialsService :  MaterialsService ) { }

  ngOnInit(): void {
    this.fetchAll();
    this.initMaterials();
  }

  private initMaterials(){
    this.materialsService.findAll().subscribe(r=>this.materialsList = r);
  }

  remove(mat){
    console.log(mat)
    this.classLevelRefService.removeMaterialFromClassLevel(mat.classLevelRef.id, mat.material.id).subscribe(()=>this.fetchAll())
  }

  private fetchAll(){
    this.classLevelRefService.findMaterialsByClassLevelId(this.classLevelRefId).subscribe(r=> this.materials = r );
  }

  addNew(){
    this.newLevelMaterial =  {
      materialId : null, levelId : this.classLevelRefId , numberOfHours : '' 
    }
    this.initValidator();
  }

  reset(){
    this.newLevelMaterial = null; 
  }

  submit(){
    console.log(this.newLevelMaterial)
  }

  private initValidator(){
    this.newMaterialFormGroup = new FormGroup({
      materialId: new FormControl(this.newLevelMaterial.materialId, [
        Validators.required,
      ]),
      numberOfHours: new FormControl(this.newLevelMaterial.materialId, [
        Validators.required,
      ])
    });
  }

  get materialId() { return this.newMaterialFormGroup.get('materialId'); }
  get numberOfHours() { return this.newMaterialFormGroup.get('numberOfHours'); }
  
}
