import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObtenerRegistroCriterioComponent } from './obtener-registro-criterio.component';

describe('ObtenerRegistroCriterioComponent', () => {
  let component: ObtenerRegistroCriterioComponent;
  let fixture: ComponentFixture<ObtenerRegistroCriterioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ObtenerRegistroCriterioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ObtenerRegistroCriterioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
