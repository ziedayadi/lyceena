import { Component, Input, OnInit } from '@angular/core';
import { ClassLevelsRefService } from 'src/app/services/class-levels-ref.service';
import { ClassesService } from 'src/app/services/classes.service';
import { MaterialsService } from 'src/app/services/materials.service';

@Component({
  selector: 'app-materials-list',
  templateUrl: './materials-list.component.html',
  styleUrls: ['./materials-list.component.css']
})
export class MaterialsListComponent implements OnInit {

  @Input() class: any;

  materials: any;
  teachers: any;
  materialToUpdate : {
    materialId, oldTeacherId , newTeacherId ,  classId
  }; 
  teachersForMaterial : any; 
  constructor(private classLevelRefService: ClassLevelsRefService,
    private classesService: ClassesService,
    private materialsRefSevice : MaterialsService) { }

  ngOnInit(): void {
    this.initData(); 
  }

  private initData(){
    this.fetchMaterials();
    this.fetchTeachers();
  }

  fetchMaterials() {
    this.classLevelRefService.findMaterialsByClassLevelId(this.class.level.id).subscribe(r => {
      this.materials = r
    })
  }

  fetchTeachers() {
    this.classesService.findTeachersByClassId(this.class.id).subscribe(r => this.teachers = r);
  }

  getTeacher(material) {
    let emptyTeacher = {
      firstName: '', lastName: ' - '
    }
    if (this.teachers) {
      let t = this.teachers.filter(t => t.material.id == material.id);
      if (t.length > 0)
        return t[0];
      else return emptyTeacher;
    } else {
      return emptyTeacher; 
    }
  }

  onUpdateTeacherForMaterial(material){
    this.materialToUpdate = {
      materialId : material.id, 
      oldTeacherId : this.getTeacher(material).id, 
      newTeacherId : this.getTeacher(material).id, 
      classId  : this.class.id
    }
    this.materialsRefSevice.findTeachersForMaterial(material.id).subscribe(r=> {
       this.teachersForMaterial = r; 
    })
  }

  onCancelChange(){
    this.materialToUpdate = null; 
  }
  onOk(){
    this.classesService.replaceTeacher(this.materialToUpdate).subscribe(()=> {
      this.initData(); 
      this.materialToUpdate = null; 
   })
  }

}
