import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

interface Todo {
  id: number;
  summary: string;
  description: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'todo-app';
  todos: Todo[] = [];
  summary = '';
  description = '';
  addView = false;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.loadTodos();
  }

  loadTodos() {
    this.http.get('http://localhost:8080/todos').subscribe(todos => {
      this.todos = todos as Todo[];
      this.addView = this.todos.length === 0;
    });
  }

  deleteTodo(id: number | undefined) {
    // @ts-ignore
    this.http.delete('http://localhost:8080/todos/' + id).subscribe((todo: Todo) => {
      this.todos = this.todos.filter(todo => todo.id !== id);
    });
  }

  addTodo() {
    const newTodo = {
      summary: this.summary,
      description: this.description
    };

    // @ts-ignore
    this.http.post('http://localhost:8080/todos', newTodo).subscribe((todo: Todo) => {
      this.todos = [...this.todos, todo];
      console.log( "tod : " + todo.id)
      this.summary = '';
      this.description = '';
      this.toggleAdd();
    });
  }

  toggleAdd() {
    this.addView = !this.addView;
  }
}
