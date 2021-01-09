package com.sanitas.clinicapp.hr;

import com.sanitas.clinicapp.hr.panels.*;
import com.sanitas.clinicapp.mr.MrController;

import com.sanitas.clinicapp.mr.panels.PanelAddPatient;
import com.sanitas.clinicapp.mr.panels.PanelEditPatient;
import com.sanitas.clinicapp.mr.panels.PanelShowPatients;
import com.sanitas.clinicapp.struct.Doctor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class HrController {

    private HrModel model;
    private HrView view;

    public HrController(HrModel model, HrView view,JFrame previousView) {
        this.model=model;
        this.view=view;

        loadListenersHr(previousView);
    }

    private void loadListenersHr(JFrame previousView) {
        PanelShowEmployee openView=view.getViewv();
        openView.addSearchButtonListener(new ButtonListenerViewEmp());
        openView.addDeleteButtonListener(new DeleteButtonListenerHr());
        openView.addModifyButtonListener(new ModifyButtonListenerHr());
        openView.addScheduleButtonListener(new ScheduleListener());
        openView.addHolidayButtonListener(new HolidayListener());

        PanelAddEmployee panelAddEmployee =new PanelAddEmployee();
        panelAddEmployee.addBtnAddEmployeeListener(new AddButtonListener());

        view.addBtnSearchEmployeeListener(new MenuButtonListenerHr(view.getBtnShowEmployees(), openView));
        view.addBtnAddEmployeesListener(new MenuButtonListenerHr(view.getBtnSearchEmployee(), panelAddEmployee));
        view.addBackListener(new BackButtonListenerHr(previousView));
    }

    class MenuButtonListenerHr implements ActionListener {
        private JPanel panel;

        public MenuButtonListenerHr(JButton button, JPanel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setRightPanel(panel);
        }

    }

    class BackButtonListenerHr implements ActionListener {

        private final JFrame previousView;

        public BackButtonListenerHr(JFrame jFrame) {
            this.previousView = jFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            previousView.setVisible(true);
        }

    }

    class DeleteButtonListenerHr implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTable employeeTable = view.getViewv().getJTable();

            int[] indexes = employeeTable.getSelectedRows();
            if (indexes.length == 0) {
                view.sendError("Trebuie sa selectezi cel putin un angajat.");

                return;
            }

            for (int index : indexes) {
                model.deleteEmployee((String) employeeTable.getValueAt(index, 3));
            }

            view.getViewv().updateTable(model.getAllData("", "", ""));
            view.sendSuccessMessage("Angajatii selectati au fost stersi.");
        }
    }

    class ScheduleListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable employeeTable = view.getViewv().getJTable();

            if (employeeTable.getSelectedRows().length != 1) {
                view.sendError("Trebuie sa selectezi exact un angajat.");
            } else {
                int row = employeeTable.getSelectedRow();
                Employee employee = model.getEmployee((String) employeeTable.getValueAt(row, 3));
                view.getViewv().setVisible(false);
                List<Schedule> schedules=model.viewSchedule(employee.getCnp());
                PanelViewSchedule panel = new PanelViewSchedule(model,schedules);
                panel.addDeleteButtonListener(new DeleteScheduleListener(employee.getCnp()));
                panel.addInsertButtonListener(new InsertScheduleListener(employee.getCnp(),schedules));
                panel.addUpdateButtonListener(new UpdateScheduleListener(employee.getCnp()));
                panel.addBackButtonListener(new BackScheduleListener());
                view.setRightPanel(panel);
            }
            employeeTable.clearSelection();
        }
    }

    class InsertScheduleListener implements ActionListener {

        String cnp;
        List<Schedule> schedules;
        public InsertScheduleListener(String cnp,List<Schedule> schedules){this.cnp=cnp; this.schedules=schedules;}

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = view.getCurrentPanel();

            if (panel instanceof PanelViewSchedule) {
                PanelViewSchedule panelViewSchedule = (PanelViewSchedule) view.getCurrentPanel();
                JTable scheduleTable = panelViewSchedule.getJTable();
                panelViewSchedule.setVisible(false);
                PanelInsertSchedule panelInsertSchedule=new PanelInsertSchedule(panelViewSchedule,schedules.get(0).getCnpEmployee());
                panelInsertSchedule.addSaveScheduleListener(new SaveScheduleListener(null,schedules.get(0).getCnpEmployee()));
                panelInsertSchedule.addCancelScheduleListener(new CancelScheduleListener());
                view.setRightPanel(panelInsertSchedule);

            }
        }
    }

    class UpdateScheduleListener implements ActionListener {

        String cnp;
        public UpdateScheduleListener(String cnp){this.cnp=cnp;}

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = view.getCurrentPanel();

            if (panel instanceof PanelViewSchedule) {
                PanelViewSchedule panelViewSchedule = (PanelViewSchedule) view.getCurrentPanel();
                JTable scheduleTable = panelViewSchedule.getJTable();
                if (scheduleTable.getSelectedRows().length != 1) {
                    view.sendError("Trebuie sa selectezi exact un rand.");
                    return;
                }
                else {
                    int row=scheduleTable.getSelectedRow();
                    Schedule schedule= panelViewSchedule.getSchedules().get(row);
                    panelViewSchedule.setVisible(false);
                    PanelEditSchedule panelEditSchedule=new PanelEditSchedule(panelViewSchedule,schedule);
                    panelEditSchedule.addSaveScheduleListener(new SaveScheduleListener(schedule,schedule.getCnpEmployee()));
                    panelEditSchedule.addCancelScheduleListener(new CancelScheduleListener());
                    view.setRightPanel(panelEditSchedule);
                }
                scheduleTable.clearSelection();

            }
        }
    }

    class SaveScheduleListener implements ActionListener {
        Schedule schedule;
        String cnp;
        SaveScheduleListener(Schedule schedule,String cnp){ this.schedule=schedule;this.cnp=cnp;}
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = view.getCurrentPanel();

            if (panel instanceof PanelEditSchedule) {
                PanelEditSchedule panelEditSchedule = (PanelEditSchedule) view.getCurrentPanel();
                boolean validation = model.updateEmployeeSchedule(panelEditSchedule.getTfCnp().getText(),
                        Integer.valueOf( panelEditSchedule.getTfMedicalUnit().getText()),
                        panelEditSchedule.getTfDay().getText(),
                        schedule.getStartHour(),schedule.getEndHour(),Time.valueOf(panelEditSchedule.getTfStart().getText()),
                        Time.valueOf(panelEditSchedule.getTfEnd().getText()),0);

                if (validation) {
                    view.sendSuccessMessage("Orarul a fost actualizat.");

                    panelEditSchedule.getPreviousPanel().updateTable(model.viewSchedule(cnp));
                } else {
                    view.sendError("Orarul NU a fost actualizat.");
                }
            }
            else
            if (panel instanceof PanelInsertSchedule) {
                PanelInsertSchedule panelInsertSchedule = (PanelInsertSchedule) view.getCurrentPanel();
                boolean validation = model.insertEmployeeSchedule(panelInsertSchedule.getTfCnp().getText(),
                        Integer.valueOf( panelInsertSchedule.getTfMedicalUnit().getText()),
                        panelInsertSchedule.getTfDay().getText(),Time.valueOf(panelInsertSchedule.getTfStart().getText()),
                        Time.valueOf(panelInsertSchedule.getTfEnd().getText()));

                if (validation) {
                    view.sendSuccessMessage("Orarul a fost actualizat.");

                    panelInsertSchedule.getPreviousPanel().updateTable(model.viewSchedule(cnp));
                } else {
                    view.sendError("Orarul NU a fost actualizat.");
                }
            }


        }

    }

    class CancelScheduleListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = view.getCurrentPanel();
            if (panel instanceof PanelEditSchedule) {
                ((PanelEditSchedule) panel).reset();
                view.setRightPanel(((PanelEditSchedule) panel).getPreviousPanel());
            }
            if (panel instanceof PanelInsertSchedule) {
                ((PanelInsertSchedule) panel).reset();
                view.setRightPanel(((PanelInsertSchedule) panel).getPreviousPanel());
            }
            if (panel instanceof PanelInsertHoliday)
            {
                ((PanelInsertHoliday)panel).reset();
                view.setRightPanel(((PanelInsertHoliday)panel).getPreviousPanel());
            }
        }

    }

    class DeleteScheduleListener implements ActionListener {

        String cnp;
        public DeleteScheduleListener(String cnp){this.cnp=cnp;}

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = view.getCurrentPanel();

            if (panel instanceof PanelViewSchedule) {
                PanelViewSchedule panelViewSchedule = (PanelViewSchedule) view.getCurrentPanel();
                JTable scheduleTable = panelViewSchedule.getJTable();
                int[] indexes = scheduleTable.getSelectedRows();
                if (indexes.length == 0) {
                    view.sendError("Trebuie sa selectezi cel putin un rand.");
                    return;
                }

                for (int index : indexes) {
                    model.deleteEmployeeSchedule(cnp,(Integer) scheduleTable.getValueAt(index, 0), (String) scheduleTable.getValueAt(index, 1), (Time) scheduleTable.getValueAt(index, 2),
                            (Time)  scheduleTable.getValueAt(index, 3),1);
                }

                panelViewSchedule.updateTable(model.viewSchedule(cnp));
                view.sendSuccessMessage("Orarul selectat a fost sters.");
            }
        }
    }

    class BackScheduleListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = view.getCurrentPanel();
            if (panel instanceof PanelViewSchedule) {
                view.setRightPanel(view.getViewv());
            }
            if (panel instanceof PanelViewHoliday) {
                view.setRightPanel(view.getViewv());
            }
        }

    }

    class HolidayListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable employeeTable = view.getViewv().getJTable();

            if (employeeTable.getSelectedRows().length != 1) {
                view.sendError("Trebuie sa selectezi exact un angajat.");
            } else {
                int row = employeeTable.getSelectedRow();
                Employee employee = model.getEmployee((String) employeeTable.getValueAt(row, 3));
                view.getViewv().setVisible(false);
                List<Holiday> holidays=model.viewHoliday(employee.getCnp());
                PanelViewHoliday panel = new PanelViewHoliday(model,holidays);
                panel.addInsertButtonListener(new InsertHolidayListener(employee.getCnp(),holidays));
                panel.addBackButtonListener(new BackScheduleListener());
                view.setRightPanel(panel);
            }
            employeeTable.clearSelection();
        }
    }

    class SaveHolidayListener implements ActionListener {
        Holiday holiday;
        String cnp;
        SaveHolidayListener(Holiday holiday,String cnp){ this.holiday=holiday;this.cnp=cnp;}
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = view.getCurrentPanel();

            if (panel instanceof PanelInsertHoliday) {
                PanelInsertHoliday panelInsertHoliday = (PanelInsertHoliday) view.getCurrentPanel();
                boolean validation = model.insertHoliday(cnp,Date.valueOf(panelInsertHoliday.getTfStart().getText()),
                        Date.valueOf( panelInsertHoliday.getTfStart().getText()),0);

                if (validation) {
                    view.sendSuccessMessage("Vacanta adaugata.");

                    panelInsertHoliday.getPreviousPanel().updateTable(model.viewHoliday(cnp));
                } else {
                    view.sendError("Vacanta NU a fost adaugata.");
                }
            }


        }

    }

    class InsertHolidayListener implements ActionListener {

        String cnp;
        List<Holiday> holidays;
        public InsertHolidayListener(String cnp,List<Holiday> holidays){this.cnp=cnp; this.holidays=holidays;}

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = view.getCurrentPanel();

            if (panel instanceof PanelViewHoliday) {
                PanelViewHoliday panelViewInsert = (PanelViewHoliday) view.getCurrentPanel();
                JTable holidayTable = panelViewInsert.getJTable();
                panelViewInsert.setVisible(false);
                PanelInsertHoliday panelInsertHoliday=new PanelInsertHoliday(panelViewInsert);
                panelInsertHoliday.addSaveHolidayListener(new SaveHolidayListener(null,holidays.get(0).getCnpEmployee()));
                panelInsertHoliday.addCancelHolidayListener(new CancelScheduleListener());
                view.setRightPanel(panelInsertHoliday);

            }
        }
    }


    class ModifyButtonListenerHr implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTable employeeTable = view.getViewv().getJTable();

            if (employeeTable.getSelectedRows().length != 1) {
                view.sendError("Trebuie sa selectezi exact un angajat.");
            } else {
                int row = employeeTable.getSelectedRow();
                Employee employee = model.getEmployee((String) employeeTable.getValueAt(row, 3));
                if(employee.getPosition().equals("Asistent Medical"))
                    employee=model.getNurse(employee);
                if(employee.getPosition().equals("Medic"))
                    employee=model.getDoctor(employee);
                view.getViewv().setVisible(false);
                PanelEditEmployee panel = new PanelEditEmployee(employee);
                panel.addSaveButtonListener(new SaveButtonListenerHr(employee));
                panel.addCancelButtonListener(new CancelButtonListenerHr());
                view.setRightPanel(panel);
            }
            employeeTable.clearSelection();
        }

    }

    class SaveButtonListenerHr implements ActionListener {
        Employee employee;
        SaveButtonListenerHr(Employee employee){ this.employee=employee;}

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = view.getCurrentPanel();

            if (panel instanceof PanelEditEmployee) {
                PanelEditEmployee panelEditEmployee = (PanelEditEmployee) view.getCurrentPanel();
                boolean validation = model.updateEmployee(panelEditEmployee.getTfCnp().getText(),
                        panelEditEmployee.getTfLastname().getText(),
                        panelEditEmployee.getTfFirstname().getText(),
                        panelEditEmployee.getTfPosition().getText());
                boolean validDoc=false;
                if(employee instanceof Doctor) {
                   validDoc = model.insertDoctor(((Employee)employee).getCnp(), panelEditEmployee.getTfsealCode().getText(),
                            Float.valueOf(panelEditEmployee.getTfcommission().getText()), panelEditEmployee.getTfscientificTitle().getText(),
                            panelEditEmployee.getTfdidacticTitle().getText());
                }
                if (validation) {
                    view.sendSuccessMessage("Datele angajatului au fost actualizate.");

                    view.getViewv().updateTable(model.getAllData("","",""));
                } else {
                    if(validDoc)
                        view.sendSuccessMessage("Datele angajatului au fost actualizate.");
                    else
                        view.sendError("Datele angajatului NU au putut fi actualizate.");
                }
            }

        }

    }

    class CancelButtonListenerHr implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = view.getCurrentPanel();
            if (panel instanceof PanelEditEmployee) {
                view.setRightPanel(view.getViewv());
            } else {
                ((PanelEditEmployee) panel).reset();
            }
        }

    }

    class ButtonListenerViewHr implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getViewv();
        }
    }

    class ButtonListenerViewEmp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel p=view.getCurrentPanel();
            if(p instanceof PanelShowEmployee) {
                PanelShowEmployee panel=(PanelShowEmployee) view.getCurrentPanel();
                String lastname = panel.getTxtLast().getText();
                String firstname = panel.getTxtFirst().getText();
                String position = panel.getTxtPosition().getText();

                List<Employee> employeeList = new ArrayList<Employee>();

                employeeList = model.getAllData(lastname, firstname, position);
                if (employeeList.isEmpty()) {
                    view.sendError("Angajatul nu poate fi gasit!");
                }
                else {
                    panel.updateTable(employeeList);
                }
            }

        }
    }

    class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            JPanel p=view.getCurrentPanel();
            List<String> txt=new ArrayList<>();
            if (p instanceof PanelAddEmployee) {
                PanelAddEmployee panel=(PanelAddEmployee)view.getCurrentPanel();
                for (int i = 0; i < 12; i++) {
                    txt.add(panel.getTxt(i));
                    if(panel.getTxt(i).isEmpty()) {
                        view.sendError("Completati toate campurile!");
                        return;
                    }
                }
                try {
                    if (model.insertEmployee(txt.get(0), txt.get(1), txt.get(2),
                            txt.get(3), txt.get(4), txt.get(5), txt.get(6),
                            Integer.parseInt(txt.get(7)), txt.get(8),
                            txt.get(9), Double.parseDouble(txt.get(10)),
                            Integer.parseInt(txt.get(11)))) {
                        view.sendSuccessMessage("Datele angajatului au fost introduse.");
                        panel.setTxtEmpty();
                    } else {
                        view.sendError("Angajat existent!");
                        //panel.setTxtEmpty();
                    }
                }catch (NumberFormatException ex) {
                    view.sendError("Date introduse gresit!");
                }
            }
        }

    }
}
