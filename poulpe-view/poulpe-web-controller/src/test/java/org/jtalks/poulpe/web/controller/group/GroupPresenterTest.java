package org.jtalks.poulpe.web.controller.group;

import org.jtalks.poulpe.model.entity.Group;
import org.jtalks.poulpe.service.GroupService;
import org.jtalks.poulpe.web.controller.DialogManager;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.matchers.AnyVararg;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GroupPresenterTest {
    GroupPresenter presenter;
    @Mock
    DialogManager dialogManager;
    @Mock
    GroupService service;
    @Mock
    GroupViewImpl viewMock;

    @Test(dataProvider = "emptyListProvider")
    public void testInitView(List<Group> returnedGroups) throws Exception {
        when(service.getAllMatchedByName(null)).thenReturn(returnedGroups);
        verify(viewMock).updateView(returnedGroups);
    }

    @DataProvider(name = "emptyListProvider")
    public Object[][] testData() {
        return new Object[][] { { new LinkedList() } };
    }

    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
        presenter = new GroupPresenter();
        presenter.setDialogManager(dialogManager);
        presenter.setGroupService(service);
        presenter.initView(viewMock);
    }

    @Test
    public void testOnEditGroup() {
        Group group = new Group();
        presenter.onEditGroup(group);
        verify(viewMock).openEditDialog(group);
    }

    @Test
    public void testOnAddGroup() {
        presenter.onAddGroup();
        verify(viewMock).openNewDialog();
    }

}
