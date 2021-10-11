import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersItemsComponent } from './users-items.component';

describe('UsersItemsComponent', () => {
  let component: UsersItemsComponent;
  let fixture: ComponentFixture<UsersItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsersItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
