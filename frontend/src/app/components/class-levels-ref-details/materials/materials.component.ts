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
  editLevelMaterial : any;  
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
    this.classLevelRefService.updateOrSaveMaterialToLevel(this.newLevelMaterial).subscribe(()=>{
      this.fetchAll(); 
      this.newLevelMaterial = null; 
    });
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

  edit(mat){
    this.editLevelMaterial = {
      levelId : mat.classLevelRef.id,
      materialId : mat.material.id, 
      numberOfHours : mat.hourNumberPerWeek
    }
  }

  saveEdit(){
    this.classLevelRefService.updateOrSaveMaterialToLevel(this.editLevelMaterial).subscribe(()=>{
      this.fetchAll(); 
      this.editLevelMaterial = null; 
    });
  }
  cancelEdit(){
    this.editLevelMaterial = null; 
  }

  get materialId() { return this.newMaterialFormGroup.get('materialId'); }
  get numberOfHours() { return this.newMaterialFormGroup.get('numberOfHours'); }
  
}
